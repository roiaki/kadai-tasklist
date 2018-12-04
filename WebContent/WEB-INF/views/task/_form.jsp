<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- (1)taglibディレクティブの指定 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
                         ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>

<label for="title">タイトル</label><br />
<input type="text" name="title" value="${task.title}" />
<br /><br />

<label for="content">内容</label><br />
<input type="text" name="content" value="${task.content}" />
<br /><br />

<label for="closing_date">締切日</label><br />
<input type="date" name="closing_date" value="${task.closing_date}" />

<input type="hidden" name="_token" value="${_token}" /> &ensp;

<button type="submit">登録</button>