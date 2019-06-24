### Описание результата
- Сохраняются Фамилия, Имя, Дата рождения, email и пароль
- Пароль хранится как SHA256 хэш
- В качестве СУБД использована InMemory H2.
- Программа реализована на Java 8 с использованием Spring Boot 

### Запуск
- запустить проект в idea, затем открыть его на localhost:8080 и открыть консоль (F12)

### Пример запросов в консоле 
``````
ПРИМЕР GET ЗАПРОСА:
fetch(
    '/users/2',
    {
        method: 'GET'
    }
    ).then(result => result.json().then(console.log))


ПРИМЕР PUT ЗАПРОСА:
fetch(
    '/users/1',
    {
        method: 'PUT',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(
                            {firstName: 'ChangeName', lastName: 'NoLastName', email: 'sdc@dc.ru', password: '1341', birthday:'1990-01-01'}
                            )
    }).then(result => result.json().then(console.log))


ПРИМЕР POST ЗАПРОСА:
fetch(
    '/users',
    {
        method: 'POST',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(
                            {firstName: 'Nikita', lastName: 'Nor', email: 'nikita@yandex.ru', password: '1341', birthday:'1996-07-15'}
                            )
    }).then(result => result.json().then(console.log))


ПРИМЕР DELETE ЗАПРОСА:
fetch(
    '/users/1',
    {
        method: 'DELETE'
    }
    ).then(result => result.json().then(console.log))

``````
