import * as React from 'react';

import { StyleSheet, View } from 'react-native';
import { MediastreamPlayerView } from 'react-native-mediastream-player';

export default function App() {
  const config = {
    id: '65a5914faad795087483ffcd',
    accountID: '5fbfd5b96660885379e1a129',
    autoplay: true,
  };
  return (
    <View style={styles.container}>
      <MediastreamPlayerView
        color="#32a852"
        style={styles.box}
        config={config}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 256,
    height: 144,
    marginVertical: 20,
  },
});
