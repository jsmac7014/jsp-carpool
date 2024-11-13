<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
  <div class="flex min-h-screen w-full justify-center items-center">
    <form method="post" action="/sign-up" class="flex flex-col gap-1 w-full max-w-md mx-auto">
      <label for="username">
        Username
      </label>
      <input type="text" id="username" name="username" placeholder="Username" class="p-2 rounded border focus:outline-none focus:ring-zinc-900 focus:ring-2"/>
      <label for="email">
        Email
      </label>
      <input type="email" id="email" name="email" placeholder="Email" class="p-2 rounded border focus:outline-none focus:ring-zinc-900 focus:ring-2"/>
      <label for="password">
        Password
      </label>
      <input type="password" id="password" name="password" placeholder="Password" class="p-2 rounded border"/>
      <button type="submit" class="p-2 text-white bg-zinc-900 rounded hover:bg-zinc-600 transition-colors">계정 만들기</button>
    </form>
  </div>
</div>
