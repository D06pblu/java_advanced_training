import { MeetingSimpleInfoDTO } from './MeetingSimpleInfoDTO';

export interface ScheduleDTO {
    start: Date;
    end: Date;
    interviews: MeetingSimpleInfoDTO[];
}