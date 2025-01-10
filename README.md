# API 문서

<aside>
💡 현재 투두와 카테고리는 자기 것만 가져오기 때문에 유저 정보는 응답에서 제외

</aside>

## 목차
- [1. 회원](#1.-회원)
- [2. 투두](#2.-투두)
- [3. 카테고리](#3.-카테고리)

## 1. 회원

### 1. 회원가입

#### Request

URL: /signup

Method: Post

```json
{
	"username" : "test",
	"password" : "test",
	"email" : "test@test.com"
}
```

#### Response

Status: 200 OK

```json
{
  "success" : true,
  "response" : {
    "id" : 4,
    "username" : "test"
  },
  "status" : 200,
  "errorMessage" : null
}
```

### 2. 로그인

#### Request

URL: /login

Method: POST

```json
{
	"username" : "test",
	"password" : "test"
}
```

#### Response

Status: 200 OK

Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpbWdVcmwiOiIvaW1hZ2VzLzEucG5nIiwic3ViIjoibWV0YWNvZGluZyIsImlkIjoxLCJleHAiOjE3MzYzMDAzNTEsInVzZXJuYW1lIjoic3NhciJ9.ShECVw5U9YADlr0eq3doJu37PaxJolgFcPULW9ZlgcJfYLyeE3la4VLVjRbP_XR653hEzHEzGzTJ4JPP04pXgg

```json
{
  "success" : true,
  "response" : {
    "id" : 4,
    "username" : "test"
  },
  "status" : 200,
  "errorMessage" : null
}
```

### 3. 자동 로그인

#### Request

URL: /auto/login

Method: POST

Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpbWdVcmwiOiIvaW1hZ2VzLzEucG5nIiwic3ViIjoibWV0YWNvZGluZyIsImlkIjoxLCJleHAiOjE3MzYzMDAzNTEsInVzZXJuYW1lIjoic3NhciJ9.ShECVw5U9YADlr0eq3doJu37PaxJolgFcPULW9ZlgcJfYLyeE3la4VLVjRbP_XR653hEzHEzGzTJ4JPP04pXgg

#### Response

Status: 200 OK

```json
{
  "success" : true,
  "response" : {
    "id" : 4,
    "username" : "test"
  },
  "status" : 200,
  "errorMessage" : null
}
```

### 4. 비밀번호 변경

#### Request

URL: /api/user/4/password

Method: PUT

Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpbWdVcmwiOiIvaW1hZ2VzLzEucG5nIiwic3ViIjoibWV0YWNvZGluZyIsImlkIjoxLCJleHAiOjE3MzYzMDAzNTEsInVzZXJuYW1lIjoic3NhciJ9.ShECVw5U9YADlr0eq3doJu37PaxJolgFcPULW9ZlgcJfYLyeE3la4VLVjRbP_XR653hEzHEzGzTJ4JPP04pXgg

```json
{
  "password" : "1234"
}
```

#### Response

Status: 200 OK

```json
{
  "success" : true,
  "response" : null,
  "status" : 200,
  "errorMessage" : null
}
```

### 5. 아이디 찾기

#### Request

URL: /find-id

Method: POST

```json
{
	"email" : "test@test.com"
}
```

#### Response

Status: 200 OK

```json
{
  "success" : true,
  "response" : {
	  "id" : 4,
	  "username" : "test"
  },
  "status" : 200,
  "errorMessage" : null
}
```

### 6. 비밀번호 재발급

#### Request

URL: /find-password

Method: PUT

```json
{
	"username" : "test",
	"email" : "test@test.com"
}
```

#### Response

Status: 200 OK

```json
{
  "success" : true,
  "response" : {
	  "password" : "1234"
  },
  "status" : 200,
  "errorMessage" : null
}
```

## 2. 투두

### 1. 카테고리 포함 투두 목록

#### Request

URL: /api/todo-category

Method: GET

Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpbWdVcmwiOiIvaW1hZ2VzLzEucG5nIiwic3ViIjoibWV0YWNvZGluZyIsImlkIjoxLCJleHAiOjE3MzYzMDAzNTEsInVzZXJuYW1lIjoic3NhciJ9.ShECVw5U9YADlr0eq3doJu37PaxJolgFcPULW9ZlgcJfYLyeE3la4VLVjRbP_XR653hEzHEzGzTJ4JPP04pXgg

#### Response

Status: 200 OK

```json
{
	"success" : true,
	"response" : {
		"categories" : [ {
			"id" : 1,
			"name" : "작업"
			},{
			"id" : 2,
			"name" : "생일"
			},{
			"id" : 3,
			"name" : "공부"
		} ],
		"todos" : [ {
			"id" : 1,
			"title" : "영어공부",
			"category" : {
				"id" : 3,
				"name" : "공부"
			},
			"memo" : "문장 1개 말하기",
			"dueDate" : "2025-01-09",
			"createdAt" : "2025-01-08T19:20:11",
			"repeat" : "매일",
			"isCompleted" : false
		},{
			"id" : 1,
			"title" : "무현 생일",
			"category" : {
				"id" : 2,
				"name" : "생일"
			},
			"memo" : "생일빵 때리기",
			"dueDate" : "2025-12-08",
			"createdAt" : "2024-12-09T09:20:11",
			"repeat" : "매년",
			"isCompleted" : false
		}	]
	},
	"status" : 200,
	"errorMessage" : null
}
```

### 2. 목록

#### Request

URL: /api/todo

Method: GET

Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpbWdVcmwiOiIvaW1hZ2VzLzEucG5nIiwic3ViIjoibWV0YWNvZGluZyIsImlkIjoxLCJleHAiOjE3MzYzMDAzNTEsInVzZXJuYW1lIjoic3NhciJ9.ShECVw5U9YADlr0eq3doJu37PaxJolgFcPULW9ZlgcJfYLyeE3la4VLVjRbP_XR653hEzHEzGzTJ4JPP04pXgg

#### Response

Status: 200 OK

```json
{
	"success" : true,
	"response" : {		
		"todos" : [ {
			"id" : 1,
			"title" : "영어공부",
			"category" : {
				"id" : 3,
				"name" : "공부"
			},
			"memo" : "문장 1개 말하기",
			"dueDate" : "2025-01-09",
			"createdAt" : "2025-01-08T19:20:11",
			"repeat" : "매일",
			"isCompleted" : false
		},{
			"id" : 1,
			"title" : "무현 생일",
			"category" : {
				"id" : 2,
				"name" : "생일"
			},
			"memo" : "생일빵 때리기",
			"dueDate" : "2025-12-08",
			"createdAt" : "2024-12-09T09:20:11",
			"repeat" : "매년",
			"isCompleted" : false
		}	]
	},
	"status" : 200,
	"errorMessage" : null
}
```

### 3. 작업 상세

#### Request

URL: /api/todo/1

Method: GET

Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpbWdVcmwiOiIvaW1hZ2VzLzEucG5nIiwic3ViIjoibWV0YWNvZGluZyIsImlkIjoxLCJleHAiOjE3MzYzMDAzNTEsInVzZXJuYW1lIjoic3NhciJ9.ShECVw5U9YADlr0eq3doJu37PaxJolgFcPULW9ZlgcJfYLyeE3la4VLVjRbP_XR653hEzHEzGzTJ4JPP04pXgg

#### Response

Status: 200 OK

```json
{
  "success" : true,
  "response" : {
	  "id" : 1,
	  "title" : "영어공부",
	  "category" : {
		  "id" : 3,
		  "name" : "공부"
	  },
	  "memo" : "문장 1개 말하기",
	  "dueDate" : "2025-01-09",
	  "createdAt" : "2025-01-08T19:20:11",
	  "repeat" : "매일",
	  "isCompleted" : false,
	  "isDeleted" : false	  
  },
  "status" : 200,
  "errorMessage" : null
}
```

### 4. 작업 추가

#### Request

URL: /api/todo

Method: POST

Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpbWdVcmwiOiIvaW1hZ2VzLzEucG5nIiwic3ViIjoibWV0YWNvZGluZyIsImlkIjoxLCJleHAiOjE3MzYzMDAzNTEsInVzZXJuYW1lIjoic3NhciJ9.ShECVw5U9YADlr0eq3doJu37PaxJolgFcPULW9ZlgcJfYLyeE3la4VLVjRbP_XR653hEzHEzGzTJ4JPP04pXgg

#### Response

Status: 200 OK

```json
{
  "success" : true,
  "response" : {
	  "id" : 1,
	  "title" : "제목없음",
	  "category" : null,
	  "memo" : "",
	  "dueDate" : "2025-01-08",
	  "createdAt" : "2025-01-08T19:20:11",
	  "repeat" : "없음",
	  "isCompleted" : false
  },
  "status" : 200,
  "errorMessage" : null
}
```

### 5. 작업 수정

#### Request

URL: /api/todo/1

Method: PUT

Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpbWdVcmwiOiIvaW1hZ2VzLzEucG5nIiwic3ViIjoibWV0YWNvZGluZyIsImlkIjoxLCJleHAiOjE3MzYzMDAzNTEsInVzZXJuYW1lIjoic3NhciJ9.ShECVw5U9YADlr0eq3doJu37PaxJolgFcPULW9ZlgcJfYLyeE3la4VLVjRbP_XR653hEzHEzGzTJ4JPP04pXgg

```json
{
	"title" : "제목있음",
	"category" : 1,
	"memo" : "메모입니다.",
	"dueDate" : "2025-01-10",
	"repeat" : "매일",
	"isCompleted" : true
}
```

<aside>
💡null인 값은 수정 안함

</aside>

#### Response

Status: 200 OK

```json
{
  "success" : true,
  "response" : null,
  "status" : 200,
  "errorMessage" : {
	  "id" : 1,
	  "title" : "제목있음",
	  "category" : {
		  "id" : 1,
		  "name" : "작업"
	  },
	  "memo" : "메모입니다",
	  "dueDate" : "2025-01-10",
	  "createdAt" : "2025-01-08T19:20:11",
	  "repeat" : "매일",
	  "isCompleted" : true
  },
  "isCompleted" : true
}
```

### 6. 삭제

#### Request

URL: /api/todo/1

Method: DELETE

Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpbWdVcmwiOiIvaW1hZ2VzLzEucG5nIiwic3ViIjoibWV0YWNvZGluZyIsImlkIjoxLCJleHAiOjE3MzYzMDAzNTEsInVzZXJuYW1lIjoic3NhciJ9.ShECVw5U9YADlr0eq3doJu37PaxJolgFcPULW9ZlgcJfYLyeE3la4VLVjRbP_XR653hEzHEzGzTJ4JPP04pXgg

#### Response

Status: 200 OK

```json
{
  "success" : true,
  "response" : null,
  "status" : 200,
  "errorMessage" : null
}
```

## 3. 카테고리

### 1. 목록

#### Request

URL: /api/category

Method: GET

Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpbWdVcmwiOiIvaW1hZ2VzLzEucG5nIiwic3ViIjoibWV0YWNvZGluZyIsImlkIjoxLCJleHAiOjE3MzYzMDAzNTEsInVzZXJuYW1lIjoic3NhciJ9.ShECVw5U9YADlr0eq3doJu37PaxJolgFcPULW9ZlgcJfYLyeE3la4VLVjRbP_XR653hEzHEzGzTJ4JPP04pXgg

#### Response

Status: 200 OK

```json
{
  "success" : true,
  "response" : {
		"categories" : [ {
			"id" : 1,
			"name" : "작업"
			},{
			"id" : 2,
			"name" : "생일"
			},{
			"id" : 3,
			"name" : "공부"
		} ]	  
  },
  "status" : 200,
  "errorMessage" : null
}
```

### 2. 카테고리 추가

#### Request

URL: /api/category

Method: POST

Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpbWdVcmwiOiIvaW1hZ2VzLzEucG5nIiwic3ViIjoibWV0YWNvZGluZyIsImlkIjoxLCJleHAiOjE3MzYzMDAzNTEsInVzZXJuYW1lIjoic3NhciJ9.ShECVw5U9YADlr0eq3doJu37PaxJolgFcPULW9ZlgcJfYLyeE3la4VLVjRbP_XR653hEzHEzGzTJ4JPP04pXgg

```json
{
	"name" : "업무"
}
```

#### Response

Status: 200 OK

```json
{
  "success" : true,
  "response" : {
	  "id" : 4,
	  "name" : "업무"
  },
  "status" : 200,
  "errorMessage" : null
}
```

### 3. 카테고리 수정

#### Request

URL: /api/category/4

Method: PUT

Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpbWdVcmwiOiIvaW1hZ2VzLzEucG5nIiwic3ViIjoibWV0YWNvZGluZyIsImlkIjoxLCJleHAiOjE3MzYzMDAzNTEsInVzZXJuYW1lIjoic3NhciJ9.ShECVw5U9YADlr0eq3doJu37PaxJolgFcPULW9ZlgcJfYLyeE3la4VLVjRbP_XR653hEzHEzGzTJ4JPP04pXgg

```json
{
	"name" : "과제"
}
```

#### Response

Status: 200 OK

```json
{
  "success" : true,
  "response" : {
	  "id" : 4,
	  "name" : "과제"
  },,
  "status" : 200,
  "errorMessage" : null
}
```

### 4. 카테고리 삭제

#### Request

URL: /api/category/4

Method: DELETE

Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpbWdVcmwiOiIvaW1hZ2VzLzEucG5nIiwic3ViIjoibWV0YWNvZGluZyIsImlkIjoxLCJleHAiOjE3MzYzMDAzNTEsInVzZXJuYW1lIjoic3NhciJ9.ShECVw5U9YADlr0eq3doJu37PaxJolgFcPULW9ZlgcJfYLyeE3la4VLVjRbP_XR653hEzHEzGzTJ4JPP04pXgg

#### Response

Status: 200 OK

```json
{
  "success" : true,
  "response" : null,
  "status" : 200,
  "errorMessage" : null
}
```
