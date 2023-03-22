import React, {useState} from 'react';
import SimpleMenteeTable from "../components/mentee/SimpleMenteeTable";
import css from '../App.module.scss';
import {FlexCell, MultiSwitch, SearchInput} from "@epam/promo";

export const MainPage = () => {
    const [onlyActive, onOnlyActiveChange] = useState(() => 'all');
    const [value] = useState('');
    return (
        <>
            <div style={{
                minHeight: '80px',
                display: 'flex',
                justifyContent: 'space-between',
                verticalAlign: 'middle',
                backgroundColor: 'white',
            }}>
                <div style={{
                    display: 'flex',
                    verticalAlign: 'middle',
                    width: '25%',
                }}>
                    <div style={{
                        fontSize: '24px',
                        display: 'flex',
                        flexFlow: 'left',
                        verticalAlign: 'middle',
                        margin: '10%',
                        fontFamily: 'Mulish',
                        color: '#292D34',
                        fontWeight: '800',
                    }}>Mentees</div>
                    <FlexCell alignSelf="flex-start" textAlign={'right'} width="auto" cx={css.container} style={{
                        display: 'flex',
                        flexFlow: 'left',
                        margin: '10%',
                        verticalAlign: 'middle',
                        textAlign: 'right',
                    }}>
                        <MultiSwitch
                            items={[
                                {id: 'all', caption: 'All'},
                                {id: 'active', caption: 'Active'},
                            ]}
                            size={"30"}
                            value={onlyActive}
                            onValueChange={onOnlyActiveChange}
                        />
                    </FlexCell>
                </div>
                <FlexCell alignSelf={'right'} width="auto" cx={css.container} style={{
                    display: 'flex',
                    margin: '2%',
                    maxHeight: '5%',
                    minWidth: '300px',
                    verticalAlign: 'middle',
                }}>

                    <SearchInput
                        value={value}
                        onValueChange={newValue => newValue ? newValue : ''}
                        placeholder="Search student"
                        debounceDelay={1000}/>
                </FlexCell>
            </div>
            {/*TODO set to props activeOnly value to connect tableFilter with the switch*/}
            <SimpleMenteeTable/>
        </>
    );
};
