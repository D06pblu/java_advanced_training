import React from "react";
import {FlexRow, FlexSpacer, Text} from "@epam/loveship";
import ReactCountryFlag from "react-country-flag";
import {Badge} from "@epam/promo";
import styles from "./StartPageCSS.module.scss"


interface StartJatInfoProps {
    language: string;
    threadCount: number;
}

const StartJatInfo: React.FC<StartJatInfoProps> = (
    {language, threadCount}) => {

    return (
        <FlexRow spacing="18" alignItems="center">
            <Text cx={styles.userInfo}>Threads count: </Text>
            <Badge cx={styles.userInfo} color='blue' fill='solid' size={'24'} caption={threadCount}/>
            <FlexSpacer/>
            <Text cx={styles.userInfo}>Language: {language}</Text>
            <ReactCountryFlag
                countryCode={language}
                svg
                style={{
                    width: '2em',
                    height: '1em',
                }}
            />
        </FlexRow>
    );
};

export default StartJatInfo;