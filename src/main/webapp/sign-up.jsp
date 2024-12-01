<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="flex flex-col h-dvh w-full justify-center items-center gap-10">
    <div class="w-full max-w-md flex flex-row items-center justify-start gap-1 px-2">
        <img src="static/icon-512.png" class="w-24 h-24 select-none"/>
        <h1 class="font-bold text-3xl select-none">TUKOREA <br/> CARPOOL</h1>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/sign-up"
          class="flex flex-col gap-1 w-full max-w-md mx-auto px-2">
        <label for="username">
            Username
        </label>
        <input type="text" id="username" name="username" placeholder="Username"
               class="p-2 rounded border focus:outline-none focus:ring-zinc-900 focus:ring-2"/>
        <label for="email">
            Email
        </label>
        <input type="email" id="email" name="email" placeholder="Email"
               class="p-2 rounded border focus:outline-none focus:ring-zinc-900 focus:ring-2"/>
        <label for="password">
            Password
        </label>
        <input type="password" id="password" name="password" placeholder="Password" class="p-2 rounded border"/>
        <button type="submit" class="p-2 text-white bg-zinc-900 rounded hover:bg-zinc-600 transition-colors">계정 만들기
        </button>
        <span class="text-sm text-center text-red-500">${error}</span>
    </form>
</div>
