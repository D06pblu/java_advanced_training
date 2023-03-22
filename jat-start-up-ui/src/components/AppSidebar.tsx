import {FlexCell, Text, Panel, Button, DropdownContainer, IconContainer} from "@epam/promo";
import {useState} from 'react';
import styles from './AppSidebar.module.scss';
import {Dropdown} from '@epam/uui-components';
import {IDropdownToggler, LinkButton} from '@epam/uui';
import {DropdownBodyProps} from '@epam/uui-core';
import {ReactComponent as menteeListLogo} from '@epam/assets/icons/common/social-group-18.svg';
import {ReactComponent as questionsLogo} from '@epam/assets/icons/common/notification-help-fill-18.svg';
import {ReactComponent as brainstormLogo} from '@epam/assets/icons/common/interest-18.svg';
import {ReactComponent as examplesLogo } from '@epam/assets/icons/common/text-format-18.svg';
import ReactCountryFlag from "react-country-flag"

export const CountryFlag = () => {
    return (
        <ReactCountryFlag
                countryCode="BY"
                svg
                style={{
                    width: '2em',
                    height: '1em',
                }}
            />
    );
};

export default function DropdownMenu() {
    const renderDropdownBody = (props: DropdownBodyProps) => {
        return (
            <DropdownContainer maxWidth={360} vPadding="24" padding="18" {...props}>
                <FlexCell alignSelf="flex-start">
                    <Text fontSize="14" lineHeight="18" color="gray90">Georgia</Text>
                </FlexCell>
                <FlexCell alignSelf="flex-start">
                    <Text fontSize="14" lineHeight="18" color="gray90">Poland</Text>
                </FlexCell>
                <FlexCell alignSelf="flex-start">
                    <Text fontSize="14" lineHeight="18" color="gray90">Kazakhstan</Text>
                </FlexCell>
            </DropdownContainer>
        );
    };

    return (
        <>
            <Dropdown renderBody={renderDropdownBody} renderTarget={(props: IDropdownToggler) => 
                 <Button cx={styles.locationButton}
                             icon={CountryFlag}
                             caption="Belarus" {...props}
                             dropdownIconPosition="right"/>} />
        </>
    );
};

export const AppSidebar = () => {
    const [value, onValueChange] = useState('List');

    return (
        <FlexCell cx={styles.jatFrame} grow={1}>
            <Panel cx={styles.jatCaption}>
                    JAT
            </Panel>
            <div className={value === 'List' ? styles.activeFrame : styles.inactiveFrame}>
                <div className={styles.menuItem}>
                    <IconContainer icon={menteeListLogo} cx={styles.logoItem} style={{fill: 'white'}}/>
                    <LinkButton cx={value === 'List' ? styles.activeMenuItemList : styles.inactiveMenuItemList} caption="List" 
                            isLinkActive={value === 'List'} 
                            onClick={() => {onValueChange('List')}}/>
                </div>
            </div>
            <div className={value === 'Brainstorms' ? styles.activeFrame : styles.inactiveFrame} style={{
                top: '22%',
            }}>
            <div className={styles.menuItem}>
                <IconContainer icon={brainstormLogo} cx={styles.logoItem} style={{fill: 'white'}}/>
                <LinkButton cx={value === 'Brainstorms' ? styles.activeMenuItemList : styles.inactiveMenuItemList} caption="Brainstorms" 
                        isLinkActive={value === 'Brainstorms'} 
                        onClick={() => onValueChange('Brainstorms')}/>
            </div>
            </div>
            <div className={value === 'Topics' ? styles.activeFrame : styles.inactiveFrame} style={{
                top: '29%',
            }}>
            <div className={styles.menuItem}>
                <IconContainer icon={questionsLogo} cx={styles.logoItem} style={{fill: 'white'}}/>
                <LinkButton cx={value === 'Topics' ? styles.activeMenuItemList : styles.inactiveMenuItemList} 
                        caption="Topics"
                        isLinkActive={value === 'Topics'} 
                        onClick={() => onValueChange('Topics')}/>
            </div>
            </div>
            <div className={value === 'Real Interviews' ? styles.activeFrame : styles.inactiveFrame} style={{
                top: '36%',
            }}>
            <div className={styles.menuItem}>
                <IconContainer icon={examplesLogo} cx={styles.logoItem} style={{fill: 'white'}}/>
                <LinkButton cx={value === 'Real Interviews' ? styles.activeMenuItemList : styles.inactiveMenuItemList} caption="Real Interviews"
                        isLinkActive={value === 'Real Interviews'} 
                        onClick={() => onValueChange('Real Interviews')}/>
            </div>
            </div>
            <div className={styles.locationItem}>
                <div className={styles.countryCaption}>
                    Country
                </div>
                <div className={styles.locationData}>
                    <DropdownMenu/>
                </div>
            </div>
        </FlexCell>
    );
}