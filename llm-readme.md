<div align="center">

<p align="center"> <img src="https://llm-assets.readthedocs.io/en/latest/_images/bigdl_logo.jpg" height="140px"><br></p>

</div>

---
## BigDL-LLM

`bigdl-llm` is a library for running **LLM** on Intel **GPU** (including integrated graphics or iGPU) and **CPU** with very low latency[^1] (for any **PyTorch** model).

[^1]: Performance varies by use, configuration and other factors. `bigdl-llm` may not optimize to the same degree for non-Intel products. Learn more at www.Intel.com/PerformanceIndex.

> *It is built on the excellent work of [llama.cpp](https://github.com/ggerganov/llama.cpp), [bitsandbytes](https://github.com/TimDettmers/bitsandbytes), [qlora](https://github.com/artidoro/qlora), [gptq](https://github.com/IST-DASLab/gptq), [AutoGPTQ](https://github.com/PanQiWei/AutoGPTQ), [awq](https://github.com/mit-han-lab/llm-awq), [AutoAWQ](https://github.com/casper-hansen/AutoAWQ), [vLLM](https://github.com/vllm-project/vllm), [llama-cpp-python](https://github.com/abetlen/llama-cpp-python), [gptq_for_llama](https://github.com/qwopqwop200/GPTQ-for-LLaMa), [chatglm.cpp](https://github.com/li-plus/chatglm.cpp), [redpajama.cpp](https://github.com/togethercomputer/redpajama.cpp), [gptneox.cpp](https://github.com/byroneverson/gptneox.cpp), [bloomz.cpp](https://github.com/NouamaneTazi/bloomz.cpp/), etc.*

### `bigdl-llm` Demos
See the ***optimized performance*** of `chatglm2-6b` and `llama-2-13b-chat` models on 12th Gen Intel Core CPU and Intel Arc GPU below.

<table width="100%">
  <tr>
    <td align="center" colspan="2">12th Gen Intel Core CPU</td>
    <td align="center" colspan="2">Intel Arc GPU</td>
  </tr>
  <tr>
    <td>
      <a href="https://llm-assets.readthedocs.io/en/latest/_images/chatglm2-6b.gif"><img src="https://llm-assets.readthedocs.io/en/latest/_images/chatglm2-6b.gif" ></a>
    </td>
    <td>
      <a href="https://llm-assets.readthedocs.io/en/latest/_images/llama-2-13b-chat.gif"><img src="https://llm-assets.readthedocs.io/en/latest/_images/llama-2-13b-chat.gif"></a>
    </td>
    <td>
      <a href="https://llm-assets.readthedocs.io/en/latest/_images/chatglm2-arc.gif"><img src="https://llm-assets.readthedocs.io/en/latest/_images/chatglm2-arc.gif"></a>
    </td>
    <td>
      <a href="https://llm-assets.readthedocs.io/en/latest/_images/llama2-13b-arc.gif"><img src="https://llm-assets.readthedocs.io/en/latest/_images/llama2-13b-arc.gif"></a>
    </td>
  </tr>
  <tr>
    <td align="center" width="25%"><code>chatglm2-6b</code></td>
    <td align="center" width="25%"><code>llama-2-13b-chat</code></td>
    <td align="center" width="25%"><code>chatglm2-6b</code></td>
    <td align="center" width="25%"><code>llama-2-13b-chat</code></td>
  </tr>
</table>

### Run on local PC (w/ iGPU acceleration)
#### Install
#### WebUI

### Run on Intel GPU and CPU using Docker

### Write PyTorch program using `bigdl-llm`

#### Install
#### Inference
#### Finetuning

### Use other libraries with `bigdl-llm`
