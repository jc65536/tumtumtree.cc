<script lang="ts">
  import { onMount } from "svelte";

  onMount(() => {
    const form = document.querySelector("form")!;
    const textarea = document.querySelector("textarea")!;
    const pre = document.querySelector("pre")!;

    form.addEventListener("submit", async (_) => {
      const code = textarea.value;
      const res = await fetch("http://localhost:5000", {
        method: "POST",
        body: code,
      });
      const parsed = await res.text();
      console.log(parsed);
    });
  });
</script>

<p>
  Thanks to Michael Lan for writing the OCaml parser.
  <a href="https://github.com/mizlan/ocaml_gram">Original project here.</a>
</p>

<form>
  <textarea></textarea>
  <button>Parse</button>
</form>

<pre></pre>

<style>
  textarea {
    width: 24rem;
    height: 12rem;
  }
</style>
