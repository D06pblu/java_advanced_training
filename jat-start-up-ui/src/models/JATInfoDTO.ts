import { ParticipantDTO } from './ParticipantDTO';
import { ScheduleDTO } from './ScheduleDTO';

export interface JATInfoDTO {
    location: string;
    language: string;
    threadCount: number;
    simpleScheduleInfo: ScheduleDTO;
    experts: ParticipantDTO[];
    mentees: ParticipantDTO[];
}