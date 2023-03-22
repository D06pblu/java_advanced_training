import React, {useState} from "react";
import {ReactComponent as leftArrowIcon} from '@epam/assets/icons/common/navigation-arrow-left-24.svg';
import {ReactComponent as rightArrowIcon} from '@epam/assets/icons/common/navigation-arrow-right-24.svg';
import {FlexRow, FlexSpacer, Text} from "@epam/loveship";
import {MeetingSimpleInfoDTO} from "../../models/MeetingSimpleInfoDTO";
import {IconButton, Panel} from "@epam/promo";
import styles from "./StartPageCSS.module.scss"


interface StartCurrentInterviewInfoProps {
    interviews: MeetingSimpleInfoDTO[]
}

const StartCurrentInterviewInfo: React.FC<StartCurrentInterviewInfoProps> = (
    {interviews}) => {

    const [interviewNum, setInterviewNum] = useState(() => 0);

    return (
        <>
            <FlexRow spacing="18" alignItems="center">
                <Text cx={styles.userInfo}>Thread:</Text>
                <Text cx={styles.hasSpaceAfter}>{"" + interviews[interviewNum].thread}</Text>
                <FlexSpacer/>
                <IconButton icon={leftArrowIcon}
                            color={'blue'}
                            isDisabled={interviewNum < 1}
                            onClick={() => {
                                setInterviewNum(interviewNum - 1);
                            }}/>
                <IconButton icon={rightArrowIcon}
                            color={'blue'}
                            isDisabled={interviewNum >= interviews.length - 1}
                            onClick={() => {
                                setInterviewNum(interviewNum + 1);
                            }}/>

            </FlexRow>
            <Panel shadow style={{margin: '2%'}}>
                <FlexRow spacing="18" alignItems="top" topShadow>
                    <Text cx={styles.userInfo}>Interviewee: </Text>
                    <Text cx={styles.userInfo} font={'sans-semibold'} color={'sky'}>
                        {interviews[interviewNum].interviewee.fullName}
                    </Text>
                </FlexRow>
            </Panel>
            <Panel shadow style={
                {
                    margin: '2%',
                    padding: '10px',
                    height: '182px',
                }}>
                {interviews[interviewNum].subjects.map(subject =>
                    <FlexRow alignItems={'top'} type={'form'}>
                        <Text cx={styles.topic}
                              font={'sans-semibold'}
                              color={'sky'}>
                            {subject.topic}
                        </Text>
                        <FlexRow alignItems={'top'} type={'form'} cx={styles.inlineThemes}>
                            {subject.themes.map(theme =>
                                <Text
                                    cx={styles.themeItem}
                                    font={'sans-semibold'}
                                    color={'fuchsia'}>
                                    {theme}
                                </Text>
                            )}
                        </FlexRow>
                    </FlexRow>
                )
                }
                <Text cx={styles.userInfo} font={'sans-semibold'} color={'sky'}>
                    {}
                </Text>
            </Panel>
        </>
    );
};

export default StartCurrentInterviewInfo;