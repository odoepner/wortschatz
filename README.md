# wortschatz

Client/Server application to build up a "Wortschatz" (DE-EN vocabulary)

Directory / File structure

- One directory per language (iso code)
- One subdirectory for each country variant (iso code)
- Ein file pro wortart (pronomen, verb, adjektive, substantiv, etc)
- Format: csv, mit kern-merkmalen + wortart-spezifischen merkmalen

Storage

- Use git for storage of the above
- Read csv file into embedded tables at startup (H2 or so)
- Use indexes for performance
- Use simple SQL for queries (no joins)
- User operations: Insert, Update, Delete
- Admin operation: Create schema (locale/language)
- Save = Export to csv, Local git commit
- Persist = git push
- Checkout / pull on startup and on user request
- Push as requested and when app shuts down


