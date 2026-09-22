class Twitter {

    private static long timeStamp = 0;

    private class User {
        int id;
        Set<Integer> followed;
        Tweet tweetHead;

        User(int id) {
            this.id = id;
            this.followed = new HashSet<>();
            this.followed.add(id);
        }

        void follow(int id) {
            followed.add(id);
        }

        void unfollow(int id) {
            if (id != this.id) {
                followed.remove(id);
            }
        }

        void post(int tweetId) {
            Tweet tweet = new Tweet(tweetId);
            tweet.next = tweetHead;
            tweetHead = tweet;
        }
    }

    private class Tweet {
        int id;
        long time;
        Tweet next;

        Tweet(int id) {
            this.id = id;
            this.time = timeStamp++;
        }
    }

    private Map<Integer, User> userMap = new HashMap<>();

    public void postTweet(int userId, int tweetId) {
        getOrCreateUser(userId).post(tweetId);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>(10);

        User user = userMap.get(userId);
        if (user == null) {
            return result;
        }

        PriorityQueue<Tweet> heap =
            new PriorityQueue<>(
                (a, b) -> Long.compare(b.time, a.time)
            );

        for (int followeeId : user.followed) {
            Tweet tweet = userMap.get(followeeId).tweetHead;

            if (tweet != null) {
                heap.offer(tweet);
            }
        }

        while (!heap.isEmpty() && result.size() < 10) {
            Tweet tweet = heap.poll();

            result.add(tweet.id);

            if (tweet.next != null) {
                heap.offer(tweet.next);
            }
        }

        return result;
    }

    public void follow(int followerId, int followeeId) {
        getOrCreateUser(followerId);
        getOrCreateUser(followeeId);

        userMap.get(followerId).follow(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        User user = userMap.get(followerId);

        if (user != null) {
            user.unfollow(followeeId);
        }
    }

    private User getOrCreateUser(int userId) {
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }

        return userMap.get(userId);
    }
}