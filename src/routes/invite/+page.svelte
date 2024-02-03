<script>
  import { page } from "$app/stores";

  const name = $page.url.searchParams.get("name");
</script>

<h2>Hi {name}</h2>
<h1>You're invited!</h1>
<dl>
  <dt>What</dt>
  <dd>It's Jason's birthday 🥳</dd>
  <dt>When</dt>
  <dd>Saturday, February 25th 7:30 p.m.</dd>
  <dt>Where</dt>
  <dd>
    <a href="https://maps.app.goo.gl/sj2kejbARsVQFeHw7" target="_blank">
      Haidilao
    </a>
  </dd>
  <dt>How</dt>
  <dd>🚌</dd>
</dl>

<form action="/invite" method="post">
  <button class="response" value="hai">
    <img src="images/tango-happy.jpg" alt="" />
    Yes
  </button>

  <button class="response" value="iya">
    <img src="images/tango-sad.jpg" alt="" />
    No
  </button>
</form>

<style>
  .response {
    display: inline-flex;
    flex-direction: column;
  }
</style>
