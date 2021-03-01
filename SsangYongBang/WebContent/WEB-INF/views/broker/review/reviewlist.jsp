<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중개사 공간</title>
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<link rel="stylesheet" type="text/css" href="/sybang/css/broker.css">
<link rel="stylesheet" type="text/css" href="/sybang/css/roomlist.css">

<style>
.title {
	text-align: left;
    padding-left: 15px;
}

</style>

</head>
<body>

	<!-- header 가져오기######## -->
	<%@include file="/WEB-INF/views/inc/header.jsp"%>
	<%@include file="/WEB-INF/views/inc/broker_left.jsp"%>

	<div class="container">
		<h1>후기/평점 조회하기</h1>
		<hr>
		<div class="list-container">
			<div class="well well-sm" style="float: left;">
				총 <b>5</b>개의 후기가 등록되어 있습니다.
			</div>
		</div>

		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col" class="text-center">번호</th>
					<th scope="col" class="text-center" style="width: 250px;">내용</th>
					<th scope="col" class="text-center">평점</th>
					<th scope="col" class="text-center">작성자</th>
					<th scope="col" class="text-center">날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.seq}</td>
					<td class="title"><a href="/sybang/broker/review/reviewview.do?seq=${dto.seq}">${dto.content}</a></td>
					<td>${dto.star}</td>
					<td>${dto.mname}</td>
					<td>${dto.regDate}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- pagination -->
	    <nav class="nav-pagination">
		  	<ul class="pagination">
			    ${pageBar}
		  	</ul>
		</nav>
		
	</div>







	<!-- footer 가져오기######## -->
	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
</body>
</html>