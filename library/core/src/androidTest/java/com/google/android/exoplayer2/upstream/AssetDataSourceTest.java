/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.net.Uri;
import android.test.InstrumentationTestCase;
import android.test.MoreAsserts;
import com.google.android.exoplayer2.testutil.TestUtil;

/**
 * Unit tests for {@link AssetDataSource}.
 */
public final class AssetDataSourceTest extends InstrumentationTestCase {

  private static final String DATA_PATH = "binary/1024_incrementing_bytes.mp3";
  private static final long DATA_LENGTH = 1024;

  public void testReadFileUri() throws Exception {
    Context context = getInstrumentation().getContext();
    AssetDataSource dataSource = new AssetDataSource(context);
    Uri assetUri = Uri.parse("file:///android_asset/" + DATA_PATH);
    DataSpec dataSpec = new DataSpec(assetUri);
    try {
      long length = dataSource.open(dataSpec);
      assertEquals(DATA_LENGTH, length);
      byte[] readData = TestUtil.readToEnd(dataSource);
      MoreAsserts.assertEquals(TestUtil.getByteArray(getInstrumentation(), DATA_PATH), readData);
    } finally {
      dataSource.close();
    }
  }

  public void testReadAssetUri() throws Exception {
    Context context = getInstrumentation().getContext();
    AssetDataSource dataSource = new AssetDataSource(context);
    Uri assetUri = Uri.parse("asset:///" + DATA_PATH);
    DataSpec dataSpec = new DataSpec(assetUri);
    try {
      long length = dataSource.open(dataSpec);
      assertEquals(DATA_LENGTH, length);
      byte[] readData = TestUtil.readToEnd(dataSource);
      MoreAsserts.assertEquals(TestUtil.getByteArray(getInstrumentation(), DATA_PATH), readData);
    } finally {
      dataSource.close();
    }
  }

}
