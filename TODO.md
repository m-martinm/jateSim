# TODO

- refactor the Gate class to be able to add a proper NOT-gate
- add Option to save and load files
- probably should "implement Serializable"
    - maybe add a static ArrayList to Engine and save everything which is displayed there and then serialize it
- implement "BlackBox"
    - puts the selected components to a blackbox and displays it as a box
- add option to remove a signal
- latches for example are not working
    - probably tracing back does not work becase gates are initially unknown
    - maybe initialize pins with low?
