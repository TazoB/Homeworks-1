package com.example.LibraryProject.Servlets;

import java.sql.Date;

public class BorrowingCopy {
    private String bookCode;
    private String name;
    private Date borrowDate;
    private Date returnDate;

    public BorrowingCopy(String bookCode, String name, Date borrowDate, Date returnDate) {
        this.bookCode = bookCode;
        this.name = name;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
