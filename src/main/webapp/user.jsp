<%--
  Created by IntelliJ IDEA.
  User: jungwoosong
  Date: 11/14/24
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="w-full min-h-screen bg-gray-100 p-2 shadow">
    <div class="max-w-lg mx-auto rounded bg-white p-4">
        <h1 class="text-3xl font-bold mb-2">회원정보</h1>
        <div class="flex flex-col gap-2">
            <div class="flex justify-between items-center">
                <span>아이디</span>
                <span>${user.username}</span>
            </div>
            <div class="flex justify-between  items-center">
                <span>이메일</span>
                <div class="flex items-center">
                    <span class="mr-2">${user.email}</span>
                    <c:if test="${!user.verified}">
                        <button class="p-1 border rounded flex items-center gap-1 text-gray-500 hover:text-black hover:border-black transition-colors" onclick="sendEmailVerification()">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                                 stroke="currentColor" class="size-5">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      d="M16.5 12a4.5 4.5 0 1 1-9 0 4.5 4.5 0 0 1 9 0Zm0 0c0 1.657 1.007 3 2.25 3S21 13.657 21 12a9 9 0 1 0-2.636 6.364M16.5 12V8.25"/>
                            </svg>
                            <span class="text-sm">인증하기</span>
                        </button>
                        <span id="status-msg"></span>
                    </c:if>
                </div>
            </div>
            <div class="flex justify-between items-center">
                <span>비밀번호</span>
                <div class="">
                    <input type="password" class="p-1 border rounded"/>
                    <button class="p-1 border rounded text-gray-500 hover:text-black hover:border-black transition-colors">
                        변경
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function sendEmailVerification() {
        alert("이메일을 전송했습니다.");

        fetch("${pageContext.request.contextPath}/verify-email", {
            method: "POST",
            body: {},
            headers: {
                "Content-Type": "application/json",
            }
        })
            .then((res) => console.log(res.json()))
            .catch((err) => console.log(err));


    }
</script>