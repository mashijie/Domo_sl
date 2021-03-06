<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/head.jsp"%>
<div>
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath }/main.html">首页</a> <span class="divider">/</span></li>
		<li><a href="${pageContext.request.contextPath }/informanage/portalafficheList.html">公告列表</a></li>
	</ul>
</div>
			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-th"></i> 公告列表</h2>
					</div>
					
					<div class="box-content">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th width="40px;" class="center"></th>
								  <th class="center">标题</th>
								  <th class="center">发布时间</th>
							  </tr>
						  </thead>   
						  <tbody>
						  
						  <c:if test="${page.items != null}">
						  <c:forEach items="${page.items}" var="affiche">
							<tr>
							
								<td class="center"><span class="icon32 icon-green icon-document"/></td>
								<td class="center"><a href="${pageContext.request.contextPath }/informanage/portalAfficheDetail.html?id=${affiche.id}">${affiche.title}</a></td>
								<td class="center"><fmt:formatDate value="${affiche.publishTime}" pattern="yyyy-MM-dd"/></td>
							</tr>
						  </c:forEach>
						 </c:if>
						  </tbody>
					  </table>   
					<div class="pagination pagination-centered">
					  <ul>
						<c:choose>
					  	<c:when test="${page.page == 1}">
					  	<li class="active"><a href="javascript:void();" title="首页">首页</a></li>
					  	</c:when>
					  	<c:otherwise>
					  	<li><a href="${pageContext.request.contextPath }/informanage/portalafficheList?p=1&s_loginCode=${s_loginCode}&s_referCode=${s_referCode}&s_roleId=${s_roleId}&s_isStart=${s_isStart}" title="首页">首页</a></li>
					  	</c:otherwise>
					   </c:choose>
						<c:if test="${page.pageCount!=null}">
							<c:forEach begin='1' end='${page.pageCount}' var="num">
								<li><a href="${pageContext.request.contextPath }/informanage/portalafficheList?p=${num}&s_loginCode=${s_loginCode}&s_referCode=${s_referCode}&s_roleId=${s_roleId}&s_isStart=${s_isStart}"
									class="number" title="${num}">${num}</a></li>
							</c:forEach>
						</c:if>
<!-- 						<li class="active"> -->
<%-- 							  <a href="#" title="${page.page}">${page.page}</a> --%>
<!-- 						</li> -->
<%-- 						<c:if test="${page.nextPages!=null}"> --%>
<%-- 							<c:forEach begin='0' end='${page.nextPages}' var="num"> --%>
<%-- 								<li><a href="${pageContext.request.contextPath }/informanage/portalafficheList.html?p=${num}" title="${num}"> --%>
<%-- 								${num} </a></li> --%>
<%-- 							</c:forEach> --%>
<%-- 						</c:if> --%>
						<c:if test="${page.pageCount !=null}">
							<c:choose>
						  	<c:when test="${page.page == page.pageCount}">
						  	<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
						  	</c:when>
						  	<c:otherwise>
						  	<li><a href="${pageContext.request.contextPath }/informanage/portalafficheList.html?p=${page.pageCount}&s_loginCode=${s_loginCode}&s_referCode=${s_referCode}&s_roleId=${s_roleId}&s_isStart=${s_isStart}" title="尾页">尾页</a></li>
						  	</c:otherwise>
						    </c:choose>
					    </c:if>
						<c:if test="${page.pageCount == null}">
						<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
					  	</c:if>
					  </ul>
				  </div>
				</div>
			</div><!--/span-->
		</div><!--/row-->

	 
<%@include file="/WEB-INF/pages/common/foot.jsp"%>
