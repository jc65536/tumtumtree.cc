<script>
  import { page } from "$app/stores";

  const name = $page.url.searchParams.get("name");
</script>

<h2>Hi {name}</h2>
<h1>You're invited!</h1>
<dl>
  <dt>When</dt>
  <dd>Saturday, February 24th 7:30 p.m.</dd>
  <dt>Where</dt>
  <dd>
    <table>
      <thead>
        <th>Number of people who say yes</th>
        <th>Place we're going</th>
      </thead>
      <tr>
        <td>&lt; 8</td>
        <td>Haidilao</td>
      </tr>
      <tr>
        <td>&geq; 8</td>
        <td>Bon Shabu</td>
      </tr>
    </table>
  </dd>
  <img src="images/tango-happy.jpg" alt="" />
  <button id="yes" type="button"> Yes </button>
  <img src="images/tango-sad.jpg" alt="" />
  <button id="no" type="button"> No </button>
</dl>
