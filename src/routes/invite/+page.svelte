<script lang="ts">
  import { enhance } from "$app/forms";
  import { page } from "$app/stores";
  import type { ActionData } from "./$types";

  const name = $page.url.searchParams.get("name") ?? "stranger";

  export let form: ActionData;
</script>

{#if form?.exclam}
  <p>{form.exclam}</p>

  {#if form.exclam == "yay!"}
    <a href="https://calendar.google.com/calendar/event?action=TEMPLATE&tmeid=NW0wMTdxOWNzZXRoM2tyNHJmdjZ1cmk5bXAgMmI4MDhiZWQ5YzUxZDJlMzgyZTIzZmFiZGMyODYwZWE3MmI4OTA4MzY4MDQ2MjI3MzZiNzZlMGVjNTIyZmYwMUBn&tmsrc=2b808bed9c51d2e382e23fabdc2860ea72b890836804622736b76e0ec522ff01%40group.calendar.google.com">
      Here's a Google Calendar event for you!
    </a>
  {/if}
{:else}
  <div id="container">
    <h2>Hi {name}</h2>
    <h1>You're invited!</h1>
    <dl>
      <dt>What</dt>
      <dd>It's Jason's birthday 🥳</dd>
      <dt>When</dt>
      <dd>Sunday, February 25th 7:30 p.m.</dd>
      <dt>Where</dt>
      <dd>
        <a href="https://maps.app.goo.gl/sj2kejbARsVQFeHw7" target="_blank">
          Haidilao
        </a>
      </dd>
      <dt>Who</dt>
      <dd>Around 8 of my friends&mdash;and you &lt;3</dd>
      <dt>How</dt>
      <dd>Bus 🚌</dd>
    </dl>

    <h2>Will you come? 👉👈</h2>

    <p>Please select one:</p>

    <form method="post" use:enhance>
      <input type="hidden" name="name" value={name} />

      <button class="response" formaction="?/hai">
        <img src="images/tango-happy.jpg" alt="" />
        Yes
      </button>

      <button class="response" formaction="?/iya">
        <img src="images/tango-sad.jpg" alt="" />
        No
      </button>
    </form>
  </div>

  <style>
    .response {
      display: inline-flex;
      flex-direction: column;
    }

    #container {
      max-width: 30rem;
      margin: auto;
      font: comic-sans;
    }
  </style>
{/if}
