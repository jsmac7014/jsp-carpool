<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="p-2 max-w-3xl mx-auto">
    <nav class="flex flex-row justify-between items-center">
        <a href="${pageContext.request.contextPath}/" class="flex flex-row items-center gap-2">
            <img src="static/icon-512.png" class="w-10 h-10"/>
            <h2 class="text-2xl font-bold ">한국공학대학교 카풀</h2>
        </a>
        <ul class="flex flex-row items-center gap-2">
            <% if (session.getAttribute("email") != null) {%>
            <li class="w-full">
                <a href="${pageContext.request.contextPath}/user" class="inline-flex justify-center items-center gap-2 border rounded p-2">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-5">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                    </svg>
                </a>
            </li>
            <li class="w-full">
                <a href="${pageContext.request.contextPath}/logout" class="p-2 border rounded inline-flex items-center justify-center">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                         stroke="currentColor" class="size-5">
                        <path stroke-linecap="round" stroke-linejoin="round"
                              d="M15.75 9V5.25A2.25 2.25 0 0 0 13.5 3h-6a2.25 2.25 0 0 0-2.25 2.25v13.5A2.25 2.25 0 0 0 7.5 21h6a2.25 2.25 0 0 0 2.25-2.25V15m3 0 3-3m0 0-3-3m3 3H9"/>
                    </svg>
                </a>
            </li>
            <% } else { %>
            <li>
                <a href="${pageContext.request.contextPath}/sign-in">로그인</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/sign-up">회원가입</a>
            </li>
            <% } %>
        </ul>
    </nav>
    <div class="mt-2 flex flex-col gap-2">
        <%--        <input class="p-4 text-xl border rounded w-full shadow h-full" placeholder="키워드를 입력하세요"/>--%>

        <%--  filter party      --%>
        <div class="w-full">
            <select class="p-2 rounded border">
                <option>차량구분</option>
                <option>택시</option>
                <option>자차</option>
            </select>
        </div>
        <%--   list of party    --%>
        <div class="grid md:grid-cols-3 grid-cols-1 gap-2">
            <a href="" class="w-full border p-3 rounded flex flex-col gap-1">
                <h1 class="text-lg font-bold">정왕역에서 택시 타실 분 모집</h1>
                <div class="flex justify-between">
                    <span class="text-zinc-400 text-sm">2024-11-14 09:52</span>
                    <div class="flex flex-row text-zinc-400 items-center gap-1">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                             stroke="currentColor" class="size-5">
                            <path stroke-linecap="round" stroke-linejoin="round"
                                  d="M18 18.72a9.094 9.094 0 0 0 3.741-.479 3 3 0 0 0-4.682-2.72m.94 3.198.001.031c0 .225-.012.447-.037.666A11.944 11.944 0 0 1 12 21c-2.17 0-4.207-.576-5.963-1.584A6.062 6.062 0 0 1 6 18.719m12 0a5.971 5.971 0 0 0-.941-3.197m0 0A5.995 5.995 0 0 0 12 12.75a5.995 5.995 0 0 0-5.058 2.772m0 0a3 3 0 0 0-4.681 2.72 8.986 8.986 0 0 0 3.74.477m.94-3.197a5.971 5.971 0 0 0-.94 3.197M15 6.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Zm6 3a2.25 2.25 0 1 1-4.5 0 2.25 2.25 0 0 1 4.5 0Zm-13.5 0a2.25 2.25 0 1 1-4.5 0 2.25 2.25 0 0 1 4.5 0Z"/>
                        </svg>
                        <span class="text-sm">
                            3/4
                        </span>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>

<a href="${pageContext.request.contextPath}/party"
   class="absolute bottom-5 right-5 bg-zinc-900 text-white rounded-full p-3 flex items-center gap-2 hover:bg-zinc-700 transition-colors">
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
         class="size-6">
        <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v6m3-3H9m12 0a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"/>
    </svg>
    모집하기
</a>
