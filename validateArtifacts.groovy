#!/usr/bin/env groovy

def validateCreatedFileWithin2Weeks() {
    Date date = new Date().format("yyyy-mm-dd") as Date
    String date2WeeksAgo = date - 14
    println 'Date 2 Weeks ago: ' + date2WeeksAgo
    sh '''
        if [[ $(gdrive list -m 100 -q "createdTime < '${date2WeeksAgo}'" | awk '/MB/{print}') ]]
        then
            echo 'There are several files above 2 weeks ago'
            gdrive delete $(gdrive list -m 100 -q "createdTime < ''${date2WeeksAgo}'" | awk '/MB/{print $1}')
        else
            echo 'There's nothing files above 2 weeks ago'
    '''
}

return this
