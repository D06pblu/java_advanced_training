import React, {useEffect} from 'react';
import {JATInfoDTO} from '../../models/JATInfoDTO';
import {FlexCell, Panel, Text} from "@epam/promo";
import StartJatInfo from "./StartJatInfo";
import StartUserList from "./StartUserList";
import StartJatThreadInfo from "./StartCurrentInterviewInfo";
import {FlexRow} from "@epam/loveship";
import styles from "./StartPageCSS.module.scss"
import StartCalendar from "./StartCalendar";

interface JatInfoProps {
    jatInfo: JATInfoDTO;
}

const StartComponent: React.FC<JatInfoProps> = ({jatInfo}) => {
    const {language, threadCount, simpleScheduleInfo, experts, mentees} = jatInfo;
    const {start, end, interviews} = simpleScheduleInfo;

    useEffect(() => {
        let curId = 1;
        mentees.map(mentee => {
            mentee.id = curId;
            curId++;
            return mentee;
        })
        experts.map(expert => {
            expert.id = curId;
            curId++;
            return expert;
        })
    });

    const curDate = new Date();
    curDate.setHours(0);
    const currentInterviews = interviews.filter(interview => {
        return new Date(interview.end) >= curDate
    })
    return (
        <FlexCell>
            <div className="content">
                <FlexRow alignItems={'top'} spacing={'6'}>
                    <div className={styles.userInfo} style={
                        {
                            minWidth: '40%',
                            maxWidth: '40%',
                            height: '380px'
                        }}>
                        <div className="jat-info">
                            <StartJatInfo language={language} threadCount={threadCount}/>
                        </div>
                        <div className="jat-thread-info">
                            <StartJatThreadInfo interviews={currentInterviews
                            }/>
                        </div>
                    </div>
                    <div className={styles.menteesList}>
                        <StartUserList listName={'Mentees'} users={mentees}/>
                    </div>
                    <div className={styles.expertsList}>
                        <StartUserList listName={'Experts'} users={experts}/>
                    </div>
                </FlexRow>
            </div>
            <Panel cx={styles.calendar}>
                <FlexRow>
                    <Text>календарь будет с </Text>
                    <Text>{start.toString()}</Text>
                    <Text> по </Text>
                    <Text>{end.toString()}</Text>
                </FlexRow>
                <StartCalendar onDateSelect={() => {}}/>
            </Panel>
        </FlexCell>
    );
};

export default StartComponent;