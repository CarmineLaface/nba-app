include(":app")

// Common modules
include(":domain")
include(":common")
include(":test")
include(":navigation:navpublic", ":navigation:navimpl")
include(":networking")
include(":base")

// Feature modules
include(":news:newsdomain", ":news:newspresentation", ":news:newsnetworking")
include(":playerdetail:playerdomain", ":playerdetail:playerpresentation", ":playerdetail:playerapi")
include(":playerlist:playerlistpresentation", ":playerlist:playerlistapi")
include(":team:teamapi", ":team:teamdomain", ":team:teampresentation")
include(":game:gamedomain", ":schedule:scheduleapi", ":schedule:schedulepresentation")
include(":ranking:rankingdomain", ":ranking:rankingnetworking", ":ranking:rankingpresentation")
include(":statistic:statspresentation", ":statistic:statsapi", ":statistic:statsdomain")
include(":game:gamepresentation", ":game:gamenetworking")
