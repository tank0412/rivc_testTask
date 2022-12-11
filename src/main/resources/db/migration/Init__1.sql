CREATE TABLE divisions (
id int identity PRIMARY KEY,
name nvarchar(150) --Наименование подразделения
parent_id int REFERENCES divisions(id) --Ссылка на родительское подразделение
dt_from datetime NOT NULL --Дата-время начала действия
dt_till datetime NULL --Дата-время завершения действия. Null = бессрочно
is_system bit DEFAULT 0 --Признак Системный (только для подразд. верхнего уровня). Системная запись должна быть неудаляемой. Опциональное условие, можно не рассматривать к выполнению.
creation_date datetime NOT NULL --Дата создания
correction_date datetime NULL --Дата последнего редактирования
);
