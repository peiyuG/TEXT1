package com.modle;

public class Book {
	private int bookID;
	private String bookName;
	private String bookClass;
	private String bookPublishing;
	private double bookPrice;
	private String bookImg;
	private String introduction;
	protected int getBookID() {
		return bookID;
	}
	protected void setBookID(int bookID) {
		this.bookID = bookID;
	}
	protected String getBookName() {
		return bookName;
	}
	protected void setBookName(String bookName) {
		this.bookName = bookName;
	}
	protected String getBookClass() {
		return bookClass;
	}
	protected void setBookClass(String bookClass) {
		this.bookClass = bookClass;
	}
	protected String getBookPublishing() {
		return bookPublishing;
	}
	protected void setBookPublishing(String bookPublishing) {
		this.bookPublishing = bookPublishing;
	}
	protected double getBookPrice() {
		return bookPrice;
	}
	protected void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	protected String getBookImg() {
		return bookImg;
	}
	protected void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}
	protected String getIntroduction() {
		return introduction;
	}
	protected void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
}
