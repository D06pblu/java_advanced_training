import React, {useEffect, useState} from 'react';
import {Route} from 'react-router-dom';
import css from './App.module.scss';
import {AppSidebar} from "./components/AppSidebar";
import StartComponent from "./components/start/StartComponent";
import {JATInfoDTO} from "./models/JATInfoDTO";
import axios from "axios";

export const App = () => {
    const [jatInfo, setJatInfo] = useState<JATInfoDTO | undefined>(undefined);

    useEffect(() => {
        axios.get<JATInfoDTO>('http://localhost:8080/api/v1/jat/1')
            .then(response => setJatInfo(response.data))
            .catch(error => console.error(error));
    }, []);

    return <div className={css.app} style={{
        display: 'inline',
    }}>
        <div style={{
            backgroundColor: 'white',
            width: '20%',
            height: '100%',
            float: 'left',
        }}>
            <Route component={AppSidebar}/>
        </div>
        <div style={{
            width: '80%',
            float: 'right',
        }}>
            <main>
                {jatInfo &&
                    <Route path="/" exact render={() => <StartComponent jatInfo={jatInfo}/>}/>
                }
            </main>
        </div>

    </div>;
}
