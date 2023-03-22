import React, {useEffect, useMemo, useState} from 'react';
import {Badge, DataTable, Panel, Text} from '@epam/promo';
import {DataColumnProps, useArrayDataSource} from '@epam/uui';
import dayjs from 'dayjs';
import {ISimpleMentee} from "../../models/SimpleMentee";

export interface SimpleMenteeTableProps {

}

export default function SimpleMenteeTable() {
    const [menteesDataSource, onMenteesDataSourceChange] = useState({});

    const [mentees, setMentees] = useState(() => [] as ISimpleMentee[])
    const [loading, setLoading] = useState(() => true);

    useEffect(() => {
        const menteeArray = [] as ISimpleMentee[];
        fetch("http://localhost:8080/api/v1/mentees", {
            method: "GET",
            headers: {"content-type": "application/json"}
        }).then(response => response.json())
            .then(data => menteeArray.push(...data));
        setMentees(menteeArray);
        setLoading(false);
    }, [loading])

    const dataSource = useArrayDataSource<ISimpleMentee, string, unknown>(
        {
            items: [],
        }, []
    );

    const view = dataSource.useView(menteesDataSource, onMenteesDataSourceChange, {
        getRowOptions: item => ({
            checkbox: {isVisible: false},
            isSelectable: item.active,
            value: item,
            isReadonly: true,
        }),
        items: mentees,
        sortBy: (item, sorting) => {
            switch (sorting.field) {
                case 'email':
                    return item.email;
                case 'name':
                    return item.fullName;
                case 'location':
                    return item.location;
                case 'participationStart':
                    return item.participationStart;
                case 'participationEnd':
                    return item.participationEnd;
                case 'leaveReason':
                    return item.leaveReason;
                case 'menteeMark':
                    return item.averageMark;
                case 'active':
                    return item.active;
            }
        },
    });

    const productColumns: DataColumnProps<ISimpleMentee>[] = useMemo(
        () => [
            {
                key: 'active',
                caption: '',
                render: mentee =>
                    mentee.active
                        ? <Badge color="green" fill="transparent" caption=""/>
                        : <Badge color="gray30" fill="transparent" caption=""/>,
                // <Checkbox value={mentee.active} onValueChange={(prev) => prev}/>,
                isSortable: true,
                isAlwaysVisible: true,
                width: 100,
            },
            {
                key: 'name',
                caption: 'Name',
                render: mentee => <Text color="gray80" font="sans-semibold">{mentee.fullName}</Text>,
                isSortable: true,
                grow: 1,
                width: 200,
            },
            {
                key: 'participationStart',
                caption: 'Registration',
                render: mentee => <Text color="gray60">{dayjs(mentee.participationStart).format('MMM D, YYYY')}</Text>,
                isSortable: true,
                isAlwaysVisible: true,
                width: 150,
            },
            {
                key: 'participationEnd',
                caption: 'Leave',
                render: mentee => <Text color="gray60">{mentee.participationEnd
                    ? dayjs(mentee.participationEnd).format('MMM D, YYYY')
                    : ''}</Text>,
                isSortable: true,
                isAlwaysVisible: true,
                width: 150,
            },
            {
                key: 'leaveReason',
                caption: 'Leave reasons',
                render: mentee => <Text>{mentee.leaveReason}</Text>,
                isSortable: true,
                width: 200,
            },
            {
                key: 'menteeMark',
                caption: 'Rate',
                render: mentee => <Text>{mentee.averageMark}</Text>,
                isSortable: true,
                width: 150,
            },
        ],
        []
    );

    if (loading) {
        return <p>'Loading...'</p>
    }

    return (
        <Panel shadow>
            <DataTable
                {...view.getListProps()}
                getRows={view.getVisibleRows}
                value={menteesDataSource}
                onValueChange={onMenteesDataSourceChange}
                columns={productColumns}
                headerTextCase="normal"
            />

        </Panel>
    );
}