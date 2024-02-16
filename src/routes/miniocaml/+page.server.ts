import { execFile } from "node:child_process";

export const actions = {
    parse: async ({ request }) => {
        const data = await request.formData();
        const src = data.get("src")?.toString();

        const dir = `${process.cwd()}/src/routes/miniocaml`;

        const path = (p: string) => `${dir}/${p}`;

        const parsed = await new Promise<string>((resolve, _reject) =>
            execFile(path("parse"),
                { cwd: path("ocaml_gram") },
                (_err, stdout, _stderr) => resolve(stdout))
                .stdin?.end(src));

        console.log("result: " + parsed);

        return { src, parsed };
    },
};
