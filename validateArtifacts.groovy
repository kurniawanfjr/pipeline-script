#!/usr/bin/env groovy

def validateCreatedFileWithin2Weeks() {
    Date date2WeeksAgo = new Date().minus(14).format("MMM dd")
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
