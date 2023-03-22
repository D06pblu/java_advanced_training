export interface ISimpleMentee {
    id: string,
    email: string,
    fullName: string,
    location: string,
    participationStart: Date,
    participationEnd: Date,
    leaveReason: string,
    averageMark: number,
    active: boolean,
    head: boolean
}