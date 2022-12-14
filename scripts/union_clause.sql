CREATE TABLE movie (
    id SERIAL PRIMARY KEY,
    name text,
    director text
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title text,
    author text
);


INSERT INTO movie (name, director)
VALUES ('���������', '����� �����'),
       ('�������', '������ ��������'),
       ('��������� �����', '����� �������'),
       ('����� ������ � ����� ��������', '�������� ������'),
       ('�������� �������', '���� �����');

INSERT INTO book (title, author)
VALUES ('����� ������ � ����� ��������', '����� �������'),
       ('��������� �����', '���� ������'),
       ('1984', '������ ������'),
       ('���������', '���� ���'),
       ('������������ �������', '����� ��������');

-- Movies based on books
SELECT name
FROM movie
INTERSECT
SELECT title
FROM book;

-- Books with no adaptation
SELECT title
FROM book
EXCEPT
SELECT name
FROM movie;

-- Movies not based on books and books with no adaptation
(SELECT name
FROM movie
UNION
SELECT title
FROM book)
EXCEPT
(SELECT name
FROM movie
INTERSECT
SELECT title
FROM book);