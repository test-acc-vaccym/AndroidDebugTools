# 使用说明



CpusetInfo.py 主要是为了实时查看每个 Task 对应的 cpuset ,以此来对 Process 的cpu 信息进行 debug.

CGroupInfo.py 主要是为了实时查看每个 Task 对应的 cgroup ,以此来对 Process 的cpu 信息进行 debug.


## Cpuset

cpuset 简单来说,就是限制一个 Task 的 cpu 使用情况. 比如我可以现在一个 Task 只运行在指定的 cpu上.

更详细的cpuset的信息可以查看这篇文档 : [cpuset](docs/cpuset.md)

## CGroup

更详细的cpuset的信息可以查看这篇文档 : [cgroup](docs/cgroup.md)
