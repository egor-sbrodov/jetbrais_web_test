## Introduction

Use `app.host` environment variable to set `YouTrack` application host. e. g. `app.host = localhost:8080` for junit configuration

Or you can run all tests using gradle:

```bash
$ gradle test
```

## Bugs:

Generally, I didn't write tests for the bugs

### Login:

* Label for login input has `:`, others have no
* Account creation with login with space throws message: `Restricted character ' ' in the name`, but there is `login` is incorrect, not `name`.
* Misunderstandable message for creating user with login like `root` and `guest`: `Removing null is prohibited`
* Message for incorrect login character `login shouldn't contain characters "<", "/", ">": login`:
    * First `login` should be replaced by `Value`.
    * I think special, chinese, japanese, arabic, cyrillic characters should be restricted for login too. E.g.: `[a-zA-Z][a-zA-Z0-9_.]`. User with login=` `(
      non-breaking space) could be created.
* Field is limited by 50 characters, but has no message about when type extra

### Password:

* Chinese and others characters are allowed, looks not good
* Not checked short/weak password
* Value is not limited

### Fullname:

* Not checked user full name for special characters as for some others.
    * E.g., if user is created with full name = ` `(space), user edit panel will have an empty username breadcrumb
* Creation users with Fullname like `root` and `guest` looks weird
* Field is limited, but has no message about

### Email/Jabber:

* Not checked **email** and **jabber** for not containing special characters, spaces, existing `@` and domain.
* Values are not limited at all. Input value could be super long, and it will be fully displayed in table.  
