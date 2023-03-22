import React from "react";
import {FlexRow, Text} from "@epam/loveship";
import styles from "./StartPageCSS.module.scss";
import {ParticipantDTO} from "../../models/ParticipantDTO";
import {Badge, Panel} from "@epam/promo";


interface StartUserListProps {
    listName: string;
    users: ParticipantDTO[];
}

const StartUserList: React.FC<StartUserListProps> = (
    {listName, users}) => {

    return (
        <>
            <Panel shadow={true} style={
                {
                    height: '50px',
                    textAlign: 'center',
                    backgroundColor: '#B5E6FF',
                    borderRadius: '10px',
                    marginBottom: '5px'
                }}>
                <FlexRow type={'form'} alignItems={'center'}>
                    <span style={
                        {
                            fontSize: "x-large",
                            marginLeft: '35%',
                        }}>
                        {listName}
                    </span>
                </FlexRow>
            </Panel>
            <Panel shadow style={{
                borderTopRightRadius: '5px',
                borderTopLeftRadius: '5px',
                height: '320px'
            }}>
                {users.map((user) => (
                    <FlexRow spacing={'18'}>
                        <Text cx={styles.listItem}>{user.fullName}</Text>
                        {user.head &&
                            <Badge color="blue" size="24" fill="semitransparent"
                                   caption="Head"/>
                        }
                    </FlexRow>
                ))}
            </Panel>
        </>
    );
};

export default StartUserList;