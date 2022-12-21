<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



 <%-- <c:import url="https://www.naver.com/" />  --%>


<c:if test="${not empty param.target }"><!-- 파라미터가  target 형태로 넘어옴 -->
   <c:import url="${param.target }" var="content" />
   <c:out value="${content }" escapeXml="${not empty param.source }" />
   	<!-- escapeXml="${param.source } 
	    check하면 파라미터 값으로 넘어와서 true 
		아무것도 체크된게 없으면 false
	  -->
</c:if>



    
    
    