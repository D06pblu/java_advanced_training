import { ChosenTopicThemeDTO } from './ChosenTopicThemeDTO';
import { ParticipantDTO } from './ParticipantDTO';

export interface MeetingSimpleInfoDTO {
    type: string;
    thread: number;
    start: Date;
    end: Date;
    interviewee: ParticipantDTO;
    subjects: ChosenTopicThemeDTO[];
}