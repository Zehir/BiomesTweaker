##BiomesTweaker

###Usage
For usage please see [Wiki](https://github.com/Zehir/BiomesTweaker/wiki)

***
[Compiling BiomesTweaker](#compiling-biomes-tweaker) - For those that want the latest unreleased features.

[Contributing](#contributing) - For those that want to help out.

###Compiling Biomes tweaker
IMPORTANT: Please report any issues you have, there might be some problems with the documentation!
Also make sure you know EXACTLY what you're doing!  It's not any of our faults if your OS crashes, becomes corrupted, etc.

***
[Setup Biomes Tweaker](#setup-biomes-tweaker)

[Compile Biomes Tweaker](#compile-biomes-tweaker)

[Updating Your Repository](#updating-your-repository)


####Setup Biomes Tweaker
This section assumes that you're using the command-line version of Git.

1. Execute `git clone https://github.com/Zehir/BiomesTweaker.git`.  This will download BiomesTweaker's source into `BiomesTweaker`.

***
	BiomesTweaker
	   \-BiomesTweaker's files (should have `build.gradle`)
***

####Compile Biomes Tweaker
1. Execute `gradle setupCiWorkspace`. This sets up Forge and downloads the necessary libraries to build Biomes Tweaker.  This might take some time, be patient.
	* You will generally only have to do this once until the Forge version in `build.properties` changes.
2. Execute `gradle build`. If you did everything right, `BUILD SUCCESSFUL` will be displayed after it finishes.  This should be relatively quick.
    * If you see `BUILD FAILED`, check the error output (it should be right around `BUILD FAILED`), fix everything (if possible), and try again.
3. Navigate to `BiomesTweaker\build\libs`.
    *  You should see a `.jar` file named `BiomesTweaker-X.X.X-X.X.X.jar`.
4. Copy the jar into your Minecraft mods folder, and you are done!

####Updating Your Repository
In order to get the most up-to-date builds, you'll have to periodically update your local repository.

1. Open up your command line.
2. Navigate to `BiomesTweaker` in the console.
3. Make sure you have not made any changes to the local repository, or else there might be issues with Git.
	* If you have, try reverting them to the status that they were when you last updated your repository.
4. Execute `git pull master`.  This pulls all commits from the official repository that do not yet exist on your local repository and updates it.

###Contributing
***
####Submitting a PullRequest
So you found a bug in zehir's code?  Think you can make it more efficient?  Want to help in general?  Great!

1. If you haven't already, create a Github account.
2. Click the `Fork` icon located at the top-right of this page (below your username).
3. Make the changes that you want to and commit them.
	* If you're making changes locally, you'll have to execute `git commit -a` and `git push` in your command line.
4. Click `Pull Request` at the right-hand side of the gray bar directly below your fork's name.
5. Click `Click to create a pull request for this comparison`, enter your PR's title, and create a detailed description telling pahimar what you changed.
6. Click `Send pull request`, and wait for feedback!

####Creating an Issue
EE3 crashes every time?  Have a suggestion?  Found a bug?  Create an issue now!

1. Make sure your issue hasn't already been answered or fixed.  Also think about whether your issue is a valid one before submitting it.
	* Please do not open an issue to ask a question.
2. Go to [the issues page](https://github.com/Zehir/BiomesTweaker/issues).
3. Click `New Issue` right below `Star` and `Fork`.
4. Enter your Issue's title (something that summarizes your issue), and then create a detailed description ("Hey pahimar, could you add/change xxx?" or "Hey, found an exploit:  stuff").
	* If you are reporting a bug report from an unofficial version, make sure you include the following:
		* Commit SHA (usually located in a changelog or the jar name itself)
		* ForgeModLoader log
		* Server log if applicable
		* Detailed description of the bug and pictures if applicable
5. Click `Submit new issue`, and wait for feedback!

***
This fils is based on pahimar's Readme.md file on his [repository](https://github.com/pahimar/Equivalent-Exchange-3)
