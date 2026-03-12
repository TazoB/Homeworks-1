CREATE TABLE IF NOT EXISTS Books (
	code VARCHAR(20) PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	author VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Members (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	join_date DATE DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS Borrowings (
	    book_code VARCHAR(20),
    member_id INT,
    borrow_date DATE,
    return_date DATE,

    PRIMARY KEY (book_code, member_id, borrow_date),

    CONSTRAINT fk_book
        FOREIGN KEY (book_code)
        REFERENCES Books(code)
        ON DELETE CASCADE,

    CONSTRAINT fk_member
        FOREIGN KEY (member_id)
        REFERENCES Members(id)
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_books_author ON Books(author);
CREATE INDEX IF NOT EXISTS idx_members_email ON Members(email);
CREATE INDEX IF NOT EXISTS idx_borrowings_book ON Borrowings(book_code);
CREATE INDEX IF NOT EXISTS idx_borrowings_member ON Borrowings(member_id);
CREATE INDEX IF NOT EXISTS idx_borrowings_date ON Borrowings(borrow_date);