document.addEventListener("DOMContentLoaded", () => {
	new Mmenu("#menu", {
		offCanvas: {
			position: "left"
		},
		theme: "black",
		counters: {
			add: true
		},
		iconbar: {
			use: true,
			top: [
				"<a href='#/'><i class='fa fa-home' aria-hidden='true'></i></a>",
				"<a href='#/'><i class='fa fa-user' aria-hidden='true'></i></a>",
			],
			bottom: [
				"<a href='#/'><i class='fa-brands fa-twitter' aria-hidden='true'></i></a>",
				"<a href='#/'><i class='fa-brands fa-facebook' aria-hidden='true'></i></a>",
				"<a href='#/'><i class='fa-brands fa-linkedin' aria-hidden='true'></i></a>",
			]
		},
		navbars: [
			{
				position: "top",
				content: ["searchfield"]
			}
		]
	});
});
