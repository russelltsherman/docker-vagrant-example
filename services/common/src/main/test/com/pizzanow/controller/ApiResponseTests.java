package com.pizzanow.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApiResponseTests {
	class TestResponseTypeImpl {
		
	}
	
	class TestResponseTypeImplInTheMiddle {
		
	}
	
	class TestResponseType{
		
	}
	
	class CrudTestResponseType {
		
	}
	
	class TestCrudResponseType {
		
	}
	
	class CrudTestResponseTypeImpl {
		
	}

	@Test
	public void testItemType() {
		ApiResponse<TestResponseType> t = new ApiResponse<TestResponseType>("test", new TestResponseType());
		assertEquals("TestResponseType", t.itemType());
	}
	
	@Test
	public void testItemTypeEndsWithImpl() {
		ApiResponse<TestResponseTypeImpl> t = new ApiResponse<TestResponseTypeImpl>("test", new TestResponseTypeImpl());
		assertEquals("TestResponseType", t.itemType());
	}

	@Test
	public void testItemTypeWithImplInTheMiddle() {
		ApiResponse<TestResponseTypeImplInTheMiddle> t = new ApiResponse<TestResponseTypeImplInTheMiddle>("test", new TestResponseTypeImplInTheMiddle());
		assertEquals("TestResponseTypeImplInTheMiddle", t.itemType());
	}
	
	@Test
	public void testItemTypeWithNull() {
		ApiResponse<TestResponseType> t = new ApiResponse<TestResponseType>("test");
		assertNull(t.itemType());
	}
	
	@Test
	public void testItemTypeBeginsWithCrud() {
		ApiResponse<CrudTestResponseType> t = new ApiResponse<CrudTestResponseType>("test", new CrudTestResponseType());
		assertEquals("TestResponseType", t.itemType());
	}

	@Test
	public void testItemTypeWithCrudInTheMiddle() {
		ApiResponse<TestCrudResponseType> t = new ApiResponse<TestCrudResponseType>("test", new TestCrudResponseType());
		assertEquals("TestCrudResponseType", t.itemType());
	}
	
	@Test
	public void testItemTypeWithCrudAndImpl() {
		ApiResponse<CrudTestResponseTypeImpl> t = new ApiResponse<CrudTestResponseTypeImpl>("test", new CrudTestResponseTypeImpl());
		assertEquals("TestResponseType", t.itemType());
	}
}
