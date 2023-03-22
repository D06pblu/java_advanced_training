import React, { useState } from 'react';
import './Calendar.css';

interface CalendarProps {
    onDateSelect: (date: Date) => void;
}


// TODO этот компонент сгенерирован при помощи Chat GPT и нужен ТОЛЬКО ДЛЯ ДЕМО. Всё нужно переделать!!!!



const Calendar: React.FC<CalendarProps> = ({ onDateSelect }) => {
    const [selectedDate, setSelectedDate] = useState<Date | null>(null);
    const [month, setMonth] = useState<number>(new Date().getMonth());
    const [year, setYear] = useState<number>(new Date().getFullYear());

    const handlePrevMonth = (): void => {
        if (month === 0) {
            setMonth(11);
            setYear(year - 1);
        } else {
            setMonth(month - 1);
        }
    };

    const handleNextMonth = (): void => {
        if (month === 11) {
            setMonth(0);
            setYear(year + 1);
        } else {
            setMonth(month + 1);
        }
    };

    const handleDateClick = (date: Date): void => {
        setSelectedDate(date);
        onDateSelect(date);
    };

    const getDaysInMonth = (month: number, year: number): number => {
        return new Date(year, month + 1, 0).getDate();
    };

    const getFirstDayOfMonth = (month: number, year: number): number => {
        return new Date(year, month, 1).getDay();
    };

    const daysInMonth = getDaysInMonth(month, year);
    const firstDayOfMonth = getFirstDayOfMonth(month, year);

    const days: Array<number> = [];
    for (let i = 1; i <= daysInMonth; i++) {
        days.push(i);
    }

    const blanks: Array<number> = [];
    for (let i = 0; i < firstDayOfMonth; i++) {
        blanks.push(i);
    }

    const totalSlots = [...blanks, ...days];
    const rows: JSX.Element[] = [];
    let cells: JSX.Element[] = [];

    totalSlots.forEach((day, i) => {
        if (i % 7 !== 0) {
            cells.push(
                <td
                    key={day}
                    className={
                        day === selectedDate?.getDate() && month === selectedDate?.getMonth() && year === selectedDate?.getFullYear()
                            ? 'today selected'
                            : 'today'
                    }
                    onClick={() => handleDateClick(new Date(year, month, day))}
                >
                    {day}
                </td>
            );
        } else {
            const insertRow = cells.slice();
            rows.push(<tr key={day}>{insertRow}</tr>);
            cells = [];
            cells.push(
                <td
                    key={day}
                    className={
                        day === selectedDate?.getDate() && month === selectedDate?.getMonth() && year === selectedDate?.getFullYear()
                            ? 'today selected'
                            : 'today'
                    }
                    onClick={() => handleDateClick(new Date(year, month, day))}
                >
                    {day}
                </td>
            );
        }
        if (i === totalSlots.length - 1) {
            const insertRow = cells.slice();
            rows.push(<tr key={day}>{insertRow}</tr>);
        }
    });

    return (
        <div className="calendar">
            <div className="header">
                <div className="month-display">{`${new Date(year, month).toLocaleString('default', { month: 'long' })} ${year}`}</div>
                <div className="nav-buttons">
                    <button className="btn-prev" onClick={handlePrevMonth}>
                        {'<'}
                    </button>
                    <button className="btn-next" onClick={handleNextMonth}>
                        {'>'}
                    </button>
                </div>
            </div>
            <table>
                <thead>
                <tr>
                    <th>Sun</th>
                    <th>Mon</th>
                    <th>Tue</th>
                    <th>Wed</th>
                    <th>Thu</th>
                    <th>Fri</th>
                    <th>Sat</th>
                </tr>
                </thead>
                <tbody>{rows}</tbody>
            </table>
        </div>
    );
};

export default Calendar;