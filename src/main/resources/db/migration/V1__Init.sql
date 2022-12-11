CREATE TABLE divisions (
id int PRIMARY KEY,
name varchar(150), --Наименование подразделения
parent_id int REFERENCES divisions(id), --Ссылка на родительское подразделение
dt_from timestamp NOT NULL, --Дата-время начала действия
dt_till timestamp NULL, --Дата-время завершения действия. Null = бессрочно
is_system boolean DEFAULT FALSE, --Признак Системный (только для подразд. верхнего уровня). Системная запись должна быть неудаляемой. Опциональное условие, можно не рассматривать к выполнению.
creation_date timestamp NOT NULL, --Дата создания
correction_date timestamp NULL --Дата последнего редактирования
);

CREATE SEQUENCE division_seq START WITH 4;