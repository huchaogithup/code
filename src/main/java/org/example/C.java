package org.example;

import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import io.lettuce.core.resource.NettyCustomizer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class C {
    public static void main(String[] args) throws InterruptedException, IllegalAccessException, NoSuchFieldException {
        System.out.println(Arrays.toString(new C().hitBricks(new int[][]{{1,1,1,0,1,1,1,1},{1,0,0,0,0,1,1,1},{1,1,1,0,0,0,1,1},{1,1,0,0,0,0,0,0},{1,0,0,0,0,0,0,0},{1,0,0,0,0,0,0,0}}, new int[][]{{4,6},{3,0},{2,3},{2,6},{4,1},{5,2},{2,1}})));
    }

    public int[] hitBricks(int[][] grid, int[][] hits) {
        for (int i = 0; i < hits.length; i++) {
            int x = hits[i][0];
            int y = hits[i][1];
            grid[x][y]--;
        }
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i] == 1) {
                p(grid,0,i);
            }
        }

        int[] res = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            int x = hits[i][0];
            int y = hits[i][1];
            grid[x][y]++;
            if (grid[x][y] == 0) {
                continue;
            }
            if (x == 0) {
                grid[x][y] = 1;
                res[i] = p(grid, x, y) - 1;
                continue;
            }
            if (hasRoot(grid, x, y)) {
                grid[x][y] = 1;
                res[i] = p(grid, x, y) - 1;
                continue;
            }
            res[i] = 0;
        }

        return res;
    }

    private static boolean hasRoot(int[][] grid, int x, int y) {
        return (x - 1 >= 0 && grid[x - 1][y] == 2) || (x + 1 < grid.length && grid[x + 1][y] == 2) || (y - 1 >= 0 && grid[x][y - 1] == 2) || (y + 1 < grid[0].length && grid[x][y + 1] == 2);
    }

    private static int p(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return 0;
        }
        if (grid[x][y] != 1) {
            return 0;
        }
        grid[x][y] = 2;
        int res = 1;
        res += p(grid, x - 1, y);
        res += p(grid, x + 1, y);
        res += p(grid, x, y - 1);
        res += p(grid, x, y + 1);
        return res;
    }


}
