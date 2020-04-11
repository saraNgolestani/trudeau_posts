# trudeau_posts
This application would crawl into CNN and Twitter in order to find last 25 posts and news of J. Trudeau and displays them.
the webdesigne of this app would be refreshed every 5minutes in order to fetch the most recent contents.

# Features:

 The search query of CNN news and username of twitter can be manual.
> - localhost:8080/content/{query}/cnn
> - localhost:8080/content/{username}/twitter
 Also the number of posts that are being fetched can be manual to and be sent as a @PathValue.
> - localhost:8080/content/cnn/{maxPosition}
> - localhost:8080/content/twitter/{maxPosition}
> - localhost:8080/content/{query}/cnn/{maxPosition}
> - localhost:8080/content/{username}/twitter/{maxPosition}
