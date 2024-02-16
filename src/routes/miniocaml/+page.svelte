<script lang="ts">
  import { enhance } from "$app/forms";
  import { page } from "$app/stores";
  import type { ActionData } from "./$types";

  export let form: ActionData;
</script>

<p>
  Thanks to Michael Lan for writing the OCaml parser.
  <a href="https://github.com/mizlan/ocaml_gram">Original project here.</a>
</p>

<form method="post" action="?/parse" use:enhance>
  {#if form?.src}
    <textarea name="src">{form.src}</textarea>
  {:else}
    <textarea name="src"></textarea>
  {/if}
  <button>Parse</button>
</form>

{#if form?.parsed}
  <pre>{form.parsed}</pre>
{/if}

<style>
  textarea {
    width: 24rem;
  }
</style>
