import fs from "node:fs";

const logResponse = (name: string, accepted: boolean) => {
    const label = accepted ? "Y" : "N";
    fs.appendFile("ignore/responses.txt",
        `${label}: ${name} - ${new Date()}\n`,
        () => { });
};

export const actions = {
    hai: async ({ request }) => {
        const data = await request.formData();
        const name = data.get("name") ?? "stranger";
        logResponse(name.toString(), true);
        return { "exclam": "yay!" };
    },
    iya: async ({ request }) => {
        const data = await request.formData();
        const name = data.get("name") ?? "stranger";
        logResponse(name.toString(), false);
        return { "exclam": "oof. No worries~" };
    },
};
