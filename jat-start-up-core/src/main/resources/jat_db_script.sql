create table if not exists public."user"
(
    id         serial8            not null,
    name       varchar(60)        not null,
    surname    varchar(60)        not null,
    email      varchar(50) unique not null,
    password   varchar,
    location   varchar(2),
    notes_link varchar(500),
    constraint user_pk primary key (id)
);

create table if not exists public.permission
(
    id   serial4 not null,
    name varchar(60),
    constraint permission_pk primary key (id)
);

create table if not exists public.m_m_user_permission
(
    user_id       bigint not null,
    permission_id bigint not null,
    constraint m_m_user_permission_pk primary key (user_id, permission_id),
    constraint m_m_user_permission_user_fk foreign key (user_id) references public."user" (id),
    constraint m_m_user_permission_permission_fk foreign key (permission_id) references public.permission (id)
);

create table if not exists public.role
(
    id   serial4 not null,
    name varchar(60),
    constraint role_pk primary key (id)
);


create table if not exists public.participation_info
(
    id                  serial8 not null,
    user_id             bigint  not null,
    jat_id              integer not null,
    participation_start date    not null,
    participation_end   date,
    constraint participation_info_pk primary key (id),
    constraint participation_info_participant_fk foreign key (user_id) references public."user" (id),
    constraint participation_info_jat_fk foreign key (jat_id) references public.jat (id)
);

create table if not exists public.m_m_role_participation_info
(
    participation_info_id bigint not null,
    role_id               bigint not null,
    constraint m_m_role_participation_info_pk primary key (participation_info_id, role_id),
    constraint m_m_role_participation_info_participation_info_fk foreign key (participation_info_id)
        references public.participation_info (id),
    constraint m_m_role_participation_info_role_fk foreign key (role_id) references public.role (id)
);

create table if not exists public.leave_reason
(
    id      serial4     not null,
    wording varchar(50) not null,
    constraint leave_reason_pk primary key (id)
);

create table if not exists public.participation_reason
(
    id      serial4     not null,
    wording varchar(50) not null,
    constraint participation_reason_pk primary key (id)
);

create table if not exists public.m_m_participation_info_leave_reason
(
    participation_info_id bigint not null,
    leave_reason_id       bigint not null,
    constraint m_m_participation_info_leave_reason_pk primary key (participation_info_id, leave_reason_id),
    constraint m_m_participation_info_leave_reason_participation_info_fk
        foreign key (participation_info_id) references public.participation_info (id),
    constraint m_m_participation_info_leave_reason_leave_reason_fk
        foreign key (leave_reason_id) references public.leave_reason (id)
);

create table if not exists public.m_m_participation_info_participation_reason
(
    participation_info_id   bigint not null,
    participation_reason_id bigint not null,
    constraint m_m_participation_info_participation_reason_pk
        primary key (participation_info_id, participation_reason_id),
    constraint m_m_participation_info_part_reason_participation_info_fk
        foreign key (participation_info_id) references public.participation_info (id),
    constraint m_m_participation_info_part_reason_participation_reason_fk
        foreign key (participation_reason_id) references public.participation_reason (id)
);

create table if not exists public.real_interview
(
    id           serial8 not null,
    project_name varchar(50),
    questions    varchar(4000),
    constraint real_interview_pk primary key (id)
);

create table if not exists public.meeting_series
(
    id         serial8   not null,
    schedule_id integer not null,
    start_date timestamp not null,
    end_date   timestamp,
    constraint meeting_series_pk primary key (id),
    constraint meeting_series_thread_schedule_fk foreign key (schedule_id) references public.thread_schedule (id)
);

create table if not exists public.meeting_series_allowed_day
(
    week_day          varchar(9) not null,
    meeting_series_id bigint     not null,
    constraint meeting_series_allowed_day_pk primary key (week_day, meeting_series_id),
    constraint m_m_meeting_series_allowed_day_meeting_series_fk
        foreign key (meeting_series_id) references public.meeting_series (id) on update cascade
);

create table if not exists public.meeting
(
    id                serial8      not null,
    meeting_series_id bigint       not null,
    date_time         timestamp    not null,
    teams_link        varchar(500) not null,
    language          varchar(2)   not null default 'EN',
    constraint meeting_pk primary key (id),
    constraint meeting_meeting_series_fk foreign key (meeting_series_id) references public.meeting_series (id)
        on update cascade
);

create table if not exists public.qa
(
    id bigint not null,
    constraint qa_pk primary key (id),
    constraint qa_meeting_fk foreign key (id) references public.meeting (id) on update cascade
);

create table if not exists public.m_m_qa_participant
(
    qa_id          bigint not null,
    participant_id bigint not null,
    constraint m_m_qa_participant_pk primary key (qa_id, participant_id),
    constraint m_m_qa_participant_qa_fk foreign key (qa_id) references public.qa (id) on update cascade,
    constraint m_m_qa_participant_participant_fk foreign key (participant_id) references public."user" (id)
        on update cascade
);

create table if not exists public.scrum
(
    id bigint not null,
    constraint scrum_pk primary key (id),
    constraint scrum_meeting_fk foreign key (id) references public.meeting (id) on update cascade
);

create table if not exists public.m_m_scrum_participant
(
    scrum_id       bigint not null,
    participant_id bigint not null,
    constraint m_m_scrum_participant_pk primary key (scrum_id, participant_id),
    constraint m_m_scrum_participant_scrum_fk foreign key (scrum_id) references public.scrum (id) on update cascade,
    constraint m_m_scrum_participant_participant_fk foreign key (participant_id) references public."user" (id)
        on update cascade
);

create table if not exists public.brainstorm
(
    id                     bigint not null,
    task                   varchar(100),
    task_description       varchar(1000),
    git_link               varchar(200),
    screen_demonstrator_id bigint,
    constraint brainstorm_pk primary key (id),
    constraint brainstorm_meeting_fk foreign key (id) references public.meeting (id) on update cascade,
    constraint brainstorm_screen_demonstrator_fk foreign key (screen_demonstrator_id) references public."user" (id)
        on update cascade
);

create table if not exists public.interview
(
    id             bigint      not null,
    type           varchar(22) not null,
    interviewee_id bigint,
    constraint interview_pk primary key (id),
    constraint interview_meeting_fk foreign key (id) references public.meeting (id) on update cascade,
    constraint interview_interviewee_fk foreign key (interviewee_id) references public."user" (id) on update cascade
);

create table if not exists public.topic
(
    id   serial8      not null,
    name varchar(100) not null,
    constraint topic_pk primary key (id)
);

create table if not exists public.m_m_interview_participant
(
    interview_id   bigint not null,
    participant_id bigint not null,
    constraint m_m_interview_participant_pk primary key (interview_id, participant_id),
    constraint m_m_interview_participant_interview_fk foreign key (interview_id) references public.interview (id)
        on update cascade,
    constraint m_m_interview_participant_participant_fk foreign key (participant_id) references public."user" (id)
        on update cascade
);

create table if not exists public.m_m_interview_theme
(
    interview_id bigint not null,
    theme_id     bigint not null,
    constraint m_m_interview_theme_pk primary key (interview_id, theme_id),
    constraint m_m_interview_theme_interview_fk foreign key (interview_id) references public.interview (id),
    constraint m_m_interview_theme_theme_fk foreign key (theme_id) references public.theme (id)
);

create table if not exists public.jat
(
    id       serial4    not null,
    location varchar(2) not null,
    language varchar(2) not null,
    constraint jat_pk primary key (id)
);

create table if not exists public.thread_schedule
(
    id     serial4 not null,
    jat_id int     not null,
    thread_num integer default 1 not null,
    constraint thread_schedule_pk primary key (id),
    constraint thread_schedule_jat_fk foreign key (jat_id) references public.jat (id)
);

create table if not exists public.feedback
(
    id           serial8 not null,
    expert_id    bigint  not null,
    interview_id bigint  not null,
    pros         varchar(500),
    contras      varchar(500),
    mark         decimal not null,
    constraint feedback_pk primary key (id),
    constraint feedback_expert_fk foreign key (expert_id) references public."user" (id),
    constraint feedback_interview_fk foreign key (interview_id) references public.interview (id)
);

create table if not exists public.gap
(
    id             serial8      not null,
    interviewee_id bigint,
    interview_id   bigint,
    name           varchar(250) not null,
    wording        varchar(1000),
    done           boolean default false,
    constraint gap_pk primary key (id),
    constraint gap_interviewee_fk foreign key (interviewee_id) references public."user" (id),
    constraint gap_interview_fk foreign key (interview_id) references public.interview (id)
);

create table if not exists public.theme
(
    id       serial8      not null,
    topic_id bigint       not null,
    name     varchar(100) not null,
    constraint theme_pk primary key (id),
    constraint theme_topic_fk foreign key (topic_id) references public.topic (id)
);

create table if not exists public.learn_hint
(
    id       serial8      not null,
    theme_id bigint       not null,
    wording  varchar(500) not null,
    constraint learn_hint_pk primary key (id),
    constraint learn_hint_theme_fk foreign key (theme_id) references public.theme (id)
);

create table if not exists public.question
(
    id       serial8      not null,
    theme_id bigint       not null,
    name     varchar(250) not null,
    answer   varchar(200),
    constraint question_pk primary key (id),
    constraint question_theme_fk foreign key (theme_id) references public.theme (id)
);

create table if not exists public.task
(
    id       serial8      not null,
    theme_id bigint       not null,
    name     varchar(250) not null,
    wording  varchar(2000),
    constraint task_pk primary key (id),
    constraint task_theme_fk foreign key (theme_id) references public.theme (id)
);

-- Constraints --
alter table public.feedback
    add constraint feedback_check check (mark > 0 and mark <= 10);

alter table public.interview
    add constraint interview_check check (type in ('FIRST_REVIEW', 'WITH_EXPERTS', 'P2P'));

alter table public.meeting_series
    add constraint meeting_series_check check (start_date <= end_date);

alter table public.meeting_series_allowed_day
    add constraint meeting_series_allowed_day_check
        check (week_day in ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'));

alter table public.participation_info
    add constraint participation_info_check
        check (participation_start <= participation_end);

alter table public."user"
    add constraint user_check check (email ~ '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$');