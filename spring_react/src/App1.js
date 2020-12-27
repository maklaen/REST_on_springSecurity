import React from 'react';
import axios from 'axios';

import './App.css';

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            students: [],
            isStudents : true,
            isMath : false,
            isRus : false,
            isLiter : false,
            student : null,
            subject : null,
            topic : null,
            type : null,
            point : 0,
            warning : "All good."
        };

        this.onMath = this.onMath.bind(this);
        this.onRus = this.onRus.bind(this);
        this.onLiter = this.onLiter.bind(this);
        this.onStudents = this.onStudents.bind(this);
        this.handleChange= this.handleChange.bind(this);
        this.onSend = this.onSend.bind(this);
    }

    onMath(){
        fetch("http://localhost:8080/math/",
            {
                method: 'GET'
            })
            .then(res => res.json())
            .then(
                (res) => {
                    this.setState({
                        isLoaded: true,
                        students: res
                    });
                },
                // Примечание: важно обрабатывать ошибки именно здесь, а не в блоке catch(),
                // чтобы не перехватывать исключения из ошибок в самих компонентах.
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }
    onRus(){
        fetch("http://localhost:8080/rus/",
            {
                method: 'GET'
            })
            .then(res => res.json())
            .then(
                (res) => {
                    this.setState({
                        isLoaded: true,
                        students: res
                    });
                },
                // Примечание: важно обрабатывать ошибки именно здесь, а не в блоке catch(),
                // чтобы не перехватывать исключения из ошибок в самих компонентах.
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }
    onLiter(){
        fetch("http://localhost:8080/liter",
            {
                method: 'GET'
            })
            .then(res => res.json())
            .then(
                (res) => {
                    this.setState({
                        isLoaded: true,
                        students: res
                    });
                },
                // Примечание: важно обрабатывать ошибки именно здесь, а не в блоке catch(),
                // чтобы не перехватывать исключения из ошибок в самих компонентах.
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }
    onStudents(){
        fetch("http://localhost:8080/students",
            {
                method: 'GET'
            })
            .then(res => res.json())
            .then(
                (res) => {
                    this.setState({
                        isLoaded: true,
                        students: res
                    });
                },
                // Примечание: важно обрабатывать ошибки именно здесь, а не в блоке catch(),
                // чтобы не перехватывать исключения из ошибок в самих компонентах.
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }

    onSend() {
        axios
            .post('http://localhost:8080/students', {
                name : this.state.student,
                subject: this.state.subject,
                topic : this.state.topic,
                type: this.state.type,
                point: this.state.point,
            })
            .then(response => {
                console.log(response);
                this.setState({
                    warning : "Отправлено, обновите данные"
                })
            })
            .catch(error => {
                console.log(error);
            });
    }

    componentDidMount() {
        fetch("http://localhost:8080/students",
            {
                method: 'GET'
            })
            .then(res => res.json())
            .then(
                (res) => {
                    this.setState({
                        isLoaded: true,
                        students: res
                    });
                },
                // Примечание: важно обрабатывать ошибки именно здесь, а не в блоке catch(),
                // чтобы не перехватывать исключения из ошибок в самих компонентах.
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }


    handleChange(event) {
        if (event.target.name === "point") {
            if (event.target.value <= 5 || event.target.value >= 0) {
                this.setState({
                    [event.target.name]: event.target.value
                });

            }
            else {
                this.setState({
                    warning : "Ошибка, введите число от 1 до 5"
                })
            }
        } else {
            this.setState({
                [event.target.name]: event.target.value
            });
        }
    }

    render() {
        if (this.state.error) {
            return <div>Ошибка: {this.state.error.message}</div>;
        } else if (!this.state.isLoaded) {
            return <div>Загрузка...</div>;
        } else {
            return (
                <div>
                    <div>
                        <label>
                            ФИО Студента :
                            <input type="text" name={"student"} value={this.state.student} onChange={this.handleChange} />
                        </label>
                        <label>
                            Предмет :
                            <input type="text" name={"subject"} value={this.state.subject} onChange={this.handleChange} />
                        </label>
                        <label>
                            Тема :
                            <input type="text" name={"topic"} value={this.state.topic} onChange={this.handleChange} />
                        </label>
                        <label>
                            Тип занятия :
                            <input type="text" name={"type"} value={this.state.type} onChange={this.handleChange} />
                        </label>
                        <label>
                            Баллы :
                            <input type="text" name={"point"} value={this.state.point} onChange={this.handleChange} />
                        </label>
                        <p>
                            <button className={"button"} onClick={this.onSend}>Отправить</button>
                        </p>
                        <label>
                            {this.state.warning}
                        </label>
                    </div>
                    <div >
                        <p>
                            <button className={"button"} onClick={this.onMath}>Математика</button>

                            <button className={"button"} onClick={this.onRus}>Русский</button>

                            <button className={"button"} onClick={this.onLiter}>Литература</button>

                            <button className={"button"} onClick={this.onStudents}>Список сутеднтиков</button>
                        </p>
                    </div>
                    <div>
                        <table className="tableStud">
                            <caption className="tableCaption"><h3>Успеваемость студентов</h3></caption>
                            <thead>
                                <tr >
                                    <th>Имя студента</th>
                                    <th>Предмет</th>
                                    <th>Тема</th>
                                    <th>Тип занятия</th>
                                    <th>Оценка</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                this.state.students.map(item => (
                                    <tr key={item.id} >
                                        <th>{item.name}</th>
                                        <th>{item.subject}</th>
                                        <th>{item.topic}</th>
                                        <th>{item.type}</th>
                                        <th>{item.point}</th>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            );
        }
    }
}



export default App;
