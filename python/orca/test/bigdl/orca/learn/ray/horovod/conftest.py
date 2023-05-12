#
# Copyright 2016 The BigDL Authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
import pytest
from bigdl.orca import init_orca_context, stop_orca_context
from pyspark.sql import SparkSession


@pytest.fixture(autouse=True, scope='package')
def orca_context_fixture():
    sc = init_orca_context(cores=8)

    spark = SparkSession(sc)
    yield
    stop_orca_context()