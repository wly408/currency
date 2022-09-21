一、基础数据类型
A、String数据类型-最大能存储512MB的数据
    1. SET/GET/APPEND/STRLEN:set：如果 key 已经持有其他值， SET 就覆写旧值，无视类型
    2. INCR/DECR/INCRBY/DECRBY:
    3. GETSET：将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
    4. SETEX: eg:SETEX mykey 60 redis,查看过期时间：TTL mykey ，Setex 命令为指定的 key 设置值及其过期时间。如果 key 已经存在， SETEX 命令将会替换旧的值。
    5. SETNX:将 key 的值设为 value ，当且仅当 key 不存在。
    6. MSET/MGET/MSETNX:
B、List数据类型
    1. LPUSH/LPUSHX/LRANGE:
    2. LPOP/LLEN:
    3. LREM/LSET/LINDEX/LTRIM:
    4. LINSERT:
    5. RPUSH/RPUSHX/RPOP/RPOPLPUSH:
C、Hash数据类型（散列类型）
    1. HSET/HGET/HDEL/HEXISTS/HLEN/HSETNX:
    2. HINCRBY：
    3. HGETALL/HKEYS/HVALS/HMGET/HMSET:
D、Set数据类型（无序集合）
    1.SADD/SMEMBERS/SCARD/SISMEMBER:
    2. SPOP/SREM/SRANDMEMBER/SMOVE:
E、Sorted Set数据类型（zset、有序集合）
    1.ZADD/ZCARD/ZCOUNT/ZREM/ZINCRBY/ZSCORE/ZRANGE/ZRANK:
    2.ZRANGEBYSCORE/ZREMRANGEBYRANK/ZREMRANGEBYSCORE：
    3.ZREVRANGE/ZREVRANGEBYSCORE/ZREVRANK:
二、命令-参考：https://www.cnblogs.com/shiguanggege/p/14229986.html
本地启动：redis-cli
远程启动：redis-cli -h host -p port -a password
Redis 连接命令
    1 AUTH password
    验证密码是否正确
    2 ECHO message
    打印字符串
    3 PING
    查看服务是否运行
    4 QUIT
    关闭当前连接
    5 SELECT index
    切换到指定的数据库

三、配置
1.常用配置redis.cof

bind 127.0.0.1
port 6379
#是否开启保护模式，默认开启。要是配置里没有指定bind和密码。开启该参数后，redis只会本地进行访问，
拒绝外部访问。要是开启了密码和bind，可以开启。否则最好关闭，设置为no
protected-mode yes
tcp-backlog 511
#redis的进程文件
pidfile /var/run/redis/redis.pid

#指定了服务端日志的级别。级别包括：debug（很多信息，方便开发、测试），verbose（许多有用的信息，
但是没有debug级别信息多），notice（适当的日志级别，适合生产环境），warn（只有非常重要的信息）
loglevel notice
 
#指定了记录日志的文件。空字符串的话，日志会打印到标准输出设备。后台运行的redis标准输出是/dev/null
logfile /usr/local/redis/var/redis.log
#数据库的数量，默认使用的数据库是0。可以通过”SELECT 【数据库序号】“命令选择一个数据库，序号从0开始
databases 16

###################################  SNAPSHOTTING  ###################################

#RDB核心规则配置 save <指定时间间隔> <执行指定次数更新操作>，满足条件就将内存中的数据同步到硬盘
中。官方出厂配置默认是 900秒内有1个更改，300秒内有10个更改以及60秒内有10000个更改，则将内存中的
数据快照写入磁盘。
若不想用RDB方案，可以把 save "" 的注释打开，下面三个注释
#   save ""
save 900 1
save 300 10
save 60 10000
 
#当RDB持久化出现错误后，是否依然进行继续进行工作，yes：不能进行工作，no：可以继续进行工作，可以通
过info中的rdb_last_bgsave_status了解RDB持久化是否有错误
stop-writes-on-bgsave-error yes
 
#配置存储至本地数据库时是否压缩数据，默认为yes。Redis采用LZF压缩方式，但占用了一点CPU的时间。若关闭该选项，
但会导致数据库文件变的巨大。建议开启。
rdbcompression yes
 
#是否校验rdb文件;从rdb格式的第五个版本开始，在rdb文件的末尾会带上CRC64的校验和。这跟有利于文件的
容错性，但是在保存rdb文件的时候，会有大概10%的性能损耗，所以如果你追求高性能，可以关闭该配置
rdbchecksum yes
 
#指定本地数据库文件名，一般采用默认的 dump.rdb
dbfilename dump.rdb
 
#数据目录，数据库的写入会在这个目录。rdb、aof文件也会写在这个目录
dir /usr/local/redis/var
###################################  SNAPSHOTTING  ###################################




2.主从配置
主Redis配置
    无需特殊配置

从Redis配置
    修改从服务器上的 redis.conf 文件：
    # slaveof <masterip> <masterport>
    # 表示当前【从服务器】对应的【主服务器】的IP是192.168.10.135，端口是6379。
    replicaof 127.0.0.1 6379


3. 哨兵
 新增哨兵配置即可？
 # 修改sentinel配置文件
 vim /usr/local/redis/6379/26379.conf
 
 修改内容：
 # 添加守护进程模式
 daemonize yes
 # 添加指明日志文件名
 logfile "/usr/local/redis/6379/sentinel26379.log"
 # 修改工作目录
 dir "/usr/local/redis/6379"
 # 修改启动端口
 port 26379
 # 添加关闭保护模式
 protected-mode no
 # 修改sentinel monitor
 sentinel monitor macrog-master 192.168.24.131 6379 2
 
 # 将配置文件中mymaster全部替换macrog-master
 # 在末行模式下 输入 :%s/mymaster/macrog-master/g
 
 依次修改26380,26381配置
 
 vim sentinel.conf?
 启动哨兵进程
 
 redis-sentinel /usr/local/redis/6379/26379.conf //或者 redis-server /usr/local/redis/6379/26379.conf --sentinel
 redis-sentinel /usr/local/redis/6380/26380.conf //或者 redis-server /usr/local/redis/6380/26380.conf --sentinel
 redis-sentinel /usr/local/redis/6381/26381.conf //或者 redis-server /usr/local/redis/6381/26381.conf --sentinel
    
四、集群-https://www.cnblogs.com/bcde/p/16587704.html
1.主从复制
    从服务器连接主服务器，发送SYNC命令；
    主服务器接收到SYNC命名后，开始执行BGSAVE命令生成RDB文件并使用缓冲区记录此后执行的所有写命令；
    主服务器BGSAVE执行完后，向所有从服务器发送快照文件，并在发送期间继续记录被执行的写命令；
    从服务器收到快照文件后丢弃所有旧数据，载入收到的快照；
    主服务器快照发送完毕后开始向从服务器发送缓冲区中的写命令；
    从服务器完成对快照的载入，开始接收命令请求，并执行来自主服务器缓冲区的写命令；（从服务器初始化完成）
    主服务器每执行一个写命令就会向从服务器发送相同的写命令，从服务器接收并执行收到的写命令（从服务器初始化完成后的操作）
 A.优点
    支持主从复制，主机会自动将数据同步到从机，可以进行读写分离
    Slave同样可以接受其它Slaves的连接和同步请求，这样可以有效的分载Master的同步压力。
    Master Server是以非阻塞的方式为Slaves提供服务。所以在Master-Slave同步期间，客户端仍然可以提交查询或修改请求。
    Slave Server同样是以非阻塞的方式完成数据同步。在同步期间，如果有客户端提交查询请求，Redis则返回同步之前的数据
B.缺点
    Redis不具备自动容错和恢复功能，主机从机的宕机都会导致前端部分读写请求失败，需要等待机器重启或者手动切换前端的IP才能恢复。
    主机宕机，宕机前有部分数据未能及时同步到从机，切换IP后还会引入数据不一致的问题，降低了系统的可用性。
    Redis较难支持在线扩容，在集群容量达到上限时在线扩容会变得很复杂。
2.哨兵
    哨兵的作用就是监控Redis系统的运行状况。它的功能包括以下两个。
    （1）监控主服务器和从服务器是否正常运行。
    （2）主服务器出现故障时自动将从服务器转换为主服务器。
哨兵的工作方式:
    每个Sentinel（哨兵）进程以每秒钟一次的频率向整个集群中的Master主服务器，Slave从服务器以及其他Sentinel（哨兵）进程发送一个 PING 命令。
    如果一个实例（instance）距离最后一次有效回复 PING 命令的时间超过 down-after-milliseconds 选项所指定的值， 则这个实例会被 Sentinel（哨兵）进程标记为主观下线（SDOWN）
    如果一个Master主服务器被标记为主观下线（SDOWN），则正在监视这个Master主服务器的所有 Sentinel（哨兵）进程要以每秒一次的频率确认Master主服务器的确进入了主观下线状态
    当有足够数量的 Sentinel（哨兵）进程（大于等于配置文件指定的值）在指定的时间范围内确认Master主服务器进入了主观下线状态（SDOWN）， 则Master主服务器会被标记为客观下线（ODOWN）
    在一般情况下， 每个 Sentinel（哨兵）进程会以每 10 秒一次的频率向集群中的所有Master主服务器、Slave从服务器发送 INFO 命令。
    当Master主服务器被 Sentinel（哨兵）进程标记为客观下线（ODOWN）时，Sentinel（哨兵）进程向下线的 Master主服务器的所有 Slave从服务器发送 INFO 命令的频率会从 10 秒一次改为每秒一次。
    若没有足够数量的 Sentinel（哨兵）进程同意 Master主服务器下线， Master主服务器的客观下线状态就会被移除。若 Master主服务器重新向 Sentinel（哨兵）进程发送 PING 命令返回有效回复，Master主服务器的主观下线状态就会被移除。
优点
    哨兵模式是基于主从模式的，所有主从的优点，哨兵模式都具有。
    主从可以自动切换，系统更健壮，可用性更高。
缺点
    Redis较难支持在线扩容，在集群容量达到上限时在线扩容会变得很复杂。

3.Redis集群模式  -数据量大时使用  
  实现了 Redis 的分布式存储，也就是说每台 Redis 节点上存储不同的数据。cluster模式为了解决单机Redis容量有限的问题，将数据按一定的规则分配到多台机器，内存/QPS不受限于单机，可受益于分布式集群高扩展性。
  
  Redis Cluster是一种服务器Sharding技术(分片和路由都是在服务端实现)，采用多主多从，每一个分区都是由一个Redis主机和多个从机组成，片区和片区之间是相互平行的。Redis Cluster集群采用了P2P的模式，完全去中心化。

五、Redis缓存穿透、击穿、雪崩
缓存穿透:指访问一个缓存和数据库中都不存在的key，由于这个key在缓存中不存在，则会到数据库中查询，数据库中也不存在该key，无法将数据添加到缓存中，所以每次都会访问数据库导致数据库压力增大。
缓存穿透解决：缓存空对象、布隆过滤器
    布隆过滤器可以判断某个数据一定不存在，但是无法判断一定存在
缓存击穿：指大量请求访问缓存中的一个key时，该key过期了，导致这些请求都去直接访问数据库，短时间大量的请求可能会将数据库击垮
缓存击穿解决：
    添加互斥锁或分布式锁，让一个线程去访问数据库，将数据添加到缓存中后，其他线程直接从缓存中获取。
    热点数据key不过期，定时更新缓存，但如果更新出问题会导致缓存中的数据一直为旧数据。

缓存雪崩：指在系统运行过程中，缓存服务宕机或大量的key值同时过期，导致所有请求都直接访问数据库导致数据库压力增大。
缓存雪崩解决：
    将key的过期时间打散，避免大量key同时过期。
    对缓存服务做高可用处理。
    加互斥锁，同一key值只允许一个线程去访问数据库，其余线程等待写入后直接从缓存中获取。
六、持久化rdb与aof
RDB持久化是指在指定的时间间隔内将内存中的数据集快照写入磁盘，实际操作过程是fork一个子进程，先将数据集写入临时文件，写入成功后，再替换之前的文件，用二进制压缩存储。
AOF持久化以日志的形式记录服务器所处理的每一个写、删除操作，查询操作不会记录，以文本的方式记录，可以打开文件看到详细的操作记录。

二者选择的标准，就是看系统是愿意牺牲一些性能，换取更高的缓存一致性（aof），还是愿意写操作频繁的时候，不启用备份来换取更高的性能，待手动运行save的时候，再做备份（rdb）。
rdb这个就更有些 eventually consistent的意思了。
rdb持久化配置
save 900 1              #在900秒(15分钟)之后，如果至少有1个key发生变化，则dump内存快照。
save 300 10            #在300秒(5分钟)之后，如果至少有10个key发生变化，则dump内存快照。
save 60 10000        #在60秒(1分钟)之后，如果至少有10000个key发生变化，则dump内存快照。

AOF持久化配置
appendfsync always     #每次有数据修改发生时都会写入AOF文件。
appendfsync everysec  #每秒钟同步一次，该策略为AOF的缺省策略。
appendfsync no          #从不同步。高效但是数据不会被持久化。
官网是不建议仅仅只使用RDB的，如果对数据丢失容忍度是有要求的，
建议是开启AOF+RDB一起用。在Redis4.0以后也支持了AOF和RDB混合，具体使用什么样的持久化方式还是要根据业务要求。

七、分布式锁
八、主从、哨兵

http://www.muzhuangnet.com/show/71369.html

查看：
info replication
